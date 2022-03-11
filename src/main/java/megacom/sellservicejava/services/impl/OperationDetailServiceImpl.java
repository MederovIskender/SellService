package megacom.sellservicejava.services.impl;

import megacom.sellservicejava.models.entities.OperationDetail;
import megacom.sellservicejava.repos.OperationDetailRepo;
import megacom.sellservicejava.services.OperationDetailService;
import org.springframework.stereotype.Service;

@Service
public class OperationDetailServiceImpl implements OperationDetailService {
    OperationDetailRepo operationDetailRepo;

    public OperationDetailServiceImpl(OperationDetailRepo operationDetailRepo) {
        this.operationDetailRepo = operationDetailRepo;
    }

    @Override
    public void saveOperationDetail(OperationDetail operationDetail) {
        operationDetailRepo.save(operationDetail);
    }
}
