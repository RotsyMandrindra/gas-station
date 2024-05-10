package hei.school.gasstation.service;

import hei.school.gasstation.model.Procurement;
import hei.school.gasstation.repository.ProcurementCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
@Service
public class ProcurementService {
    private ProcurementCrudOperation procurementCrudOperation;

    public ProcurementService(ProcurementCrudOperation procurementCrudOperation) {
        this.procurementCrudOperation = procurementCrudOperation;
    }
    public List<Procurement> getAllProcurement() throws SQLException {
        return procurementCrudOperation.findAll();
    }
    public Procurement createProcurement(Procurement toSave) throws SQLException {
        return procurementCrudOperation.save(toSave);
    }
    public Procurement updateProcurement(UUID id, Procurement toUpdate) throws SQLException {
        return procurementCrudOperation.update(id, toUpdate);
    }
    public void deleteProcurement(UUID id) throws SQLException {
        procurementCrudOperation.delete(id);
    }
    public List<Procurement> getProcurementById(UUID id) throws SQLException {
        return procurementCrudOperation.findById(id);
    }
    public double getSumRemainingQuantity() throws SQLException{
        return procurementCrudOperation.getSumRemainingQuantity();
    }
}
