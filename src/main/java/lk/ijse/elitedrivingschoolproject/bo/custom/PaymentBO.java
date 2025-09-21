package lk.ijse.elitedrivingschoolproject.bo.custom;

import lk.ijse.elitedrivingschoolproject.dto.PaymentDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentBO {

    public List<PaymentDTO> getAllPayments() throws Exception;

    public String getLastPaymnetId() throws Exception;

    public boolean savePayment(PaymentDTO payment) throws Exception;

    public boolean updatePayment(PaymentDTO payment) throws Exception;

    public boolean deletePayment(String id) throws Exception;

    public List<String> getAllPaymentIds() throws Exception;

    public Optional<PaymentDTO> findByPaymentId(String id) throws Exception;

    public String generateNewPaymentId() throws Exception;
}
