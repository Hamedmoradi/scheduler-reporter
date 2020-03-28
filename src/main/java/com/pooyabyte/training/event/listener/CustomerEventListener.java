package com.pooyabyte.training.event.listener;

import com.pooyabyte.training.domain.Log;
import com.pooyabyte.training.enums.ActionType;
import com.pooyabyte.training.enums.ServiceName;
import com.pooyabyte.training.event.CustomerEvent;
import com.pooyabyte.training.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Date;

@Component
public class CustomerEventListener implements ApplicationListener<CustomerEvent> {
@Autowired
private LogService logService;


@Override
public void onApplicationEvent(CustomerEvent event) {
	System.out.println("customer " + event.getMessage() + " with details : " + event.getCustomer());
	Log log=new Log();
	log.setCustomerId(event.getCustomer());
	log.setContent("json");//TODO maybe a JSON value
	log.setServiceName(ServiceName.CUSTOMER_REGISTRATION.getName());
	log.setAction(ActionType.INSERT.getType());
	log.setSuccessRate(true);
//	log.setLastModifiedDate(new Date(event.getCustomer().getModifiedDate()));
//	log.setLastModifiedBy(event.getCustomer().getLastModifiedBy());
//	log.setCreationDate(new Date(event.getCustomer().getCreatedBy()));
//	log.setCreatedBy(event.getCustomer().getCreatedBy());
	logService.save(log);
	
}

@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
public void handleCustom(CustomerEvent event) {
	System.out.println("Handling event inside a transaction BEFORE COMMIT.");
}

}
