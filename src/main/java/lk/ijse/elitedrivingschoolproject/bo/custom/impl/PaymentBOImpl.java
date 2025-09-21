package lk.ijse.elitedrivingschoolproject.bo.custom.impl;

import lk.ijse.elitedrivingschoolproject.bo.custom.PaymentBO;
import lk.ijse.elitedrivingschoolproject.bo.exception.DuplicateException;
import lk.ijse.elitedrivingschoolproject.bo.exception.NotFoundException;
import lk.ijse.elitedrivingschoolproject.bo.util.EntityDTOConverter;
import lk.ijse.elitedrivingschoolproject.dao.DAOFactory;
import lk.ijse.elitedrivingschoolproject.dao.DAOTypes;
import lk.ijse.elitedrivingschoolproject.dao.custom.PaymentDAO;
import lk.ijse.elitedrivingschoolproject.dto.PaymentDTO;
import lk.ijse.elitedrivingschoolproject.entity.Payments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentBOImpl implements PaymentBO {

    private final PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOTypes.PAYMENTS);
    private final EntityDTOConverter entityDTOConverter = new EntityDTOConverter();


    @Override
    public List<PaymentDTO> getAllPayments() throws Exception {

        List<Payments> payments = paymentDAO.getAll();
        List<PaymentDTO> paymentDTOs = new ArrayList<>();
        for (Payments payment : payments) {
            paymentDTOs.add(entityDTOConverter.getPaymentDTO(payment));
        }
        return paymentDTOs;
    }

    @Override
    public String getLastPaymnetId() throws Exception {

        return paymentDAO.getLastId();

    }

    @Override
    public boolean savePayment(PaymentDTO payment) throws Exception {

        Optional<Payments> payments = paymentDAO.findById(payment.getTransactionId());
        if (payments.isPresent()) {
            throw new DuplicateException("Payment already exists");
        }
        if (payment.getStudentId() == null) {
            throw new NotFoundException("Student Id is null");
        }
        return paymentDAO.save(entityDTOConverter.getPaymentEntity(payment));
    }

    @Override
    public boolean updatePayment(PaymentDTO payment) throws Exception {

        Optional<Payments> payments =paymentDAO.findById(payment.getTransactionId());
        if (payments.isEmpty()) {
            throw new DuplicateException("Payment not found");
        }
        return paymentDAO.update(entityDTOConverter.getPaymentEntity(payment));
    }

    @Override
    public boolean deletePayment(String id) throws Exception {

        Optional<Payments> payments =paymentDAO.findById(id);
        if (payments.isEmpty()) {
            throw new DuplicateException("Payment not found");
        }
        return paymentDAO.delete(id);
    }

    @Override
    public List<String> getAllPaymentIds() throws Exception {

        return paymentDAO.getAllIds();
    }

    @Override
    public Optional<PaymentDTO> findByPaymentId(String id) throws Exception {

        Optional<Payments> payments = paymentDAO.findById(id);
        if (payments.isPresent()) {
            return Optional.of(entityDTOConverter.getPaymentDTO(payments.get()));
        }
        return Optional.empty();
    }

    @Override
    public String generateNewPaymentId() throws Exception {

        return paymentDAO.generateNewId();
    }

}
