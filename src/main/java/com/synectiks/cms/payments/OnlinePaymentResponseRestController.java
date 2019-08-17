package com.synectiks.cms.payments;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.constant.CmsConstants;

/**
 * REST controller for managing payment response message data.
 */
@RestController
@RequestMapping("/api")
public class OnlinePaymentResponseRestController {

    private final Logger logger = LoggerFactory.getLogger(OnlinePaymentResponseRestController.class);
    
    @RequestMapping(method = RequestMethod.POST, value = "/cmspaymentresponse")
    public void getMessage(@RequestParam String msg, HttpServletResponse response) throws IOException, InvalidKeyException, NoSuchAlgorithmException  {
    	logger.debug("Response from payment gateway : "+msg);
    	String resp[] = msg.split("\\|");
    	String orgInput = msg.substring(0, msg.lastIndexOf("|"));
    	String orgCheckSum = msg.substring(msg.lastIndexOf("|")+1);
    	String txnStatus = resp[14];
    	String txnDate = resp[13].replaceAll(" ", "~");
    	logger.debug("txn date : "+txnDate);
    	boolean isValidResp = CheckSumGenerator.validateResponse(orgInput, orgCheckSum);
    	if(!isValidResp) {
    		txnStatus = CmsConstants.PAYMENT_STATUS_FAILED;
        }
    	
    	String a = String.format("%s?txnRefNo=%s&txnAmt=%s&txnStatus=%s&txnDate=%s", CmsConstants.PAYMENT_REDIRECT_URL,resp[2],resp[4],txnStatus,txnDate);
    	response.sendRedirect(a);
    	
    }
    
//    public static void main(String ar[]) throws InvalidKeyException, NoSuchAlgorithmException {
//    	String msg = "HMACUAT|SYN020193517033500027|U4560001031594|NA|678.00|456|524482|02|INR|DIRECT|NA|NA|0.00|17-08-2019 15:35:57|0399|NA|NA|NA|NA|NA|NA|NA|NA|NA|PME10017-Card not allowed|0FFA70E1B620D8D0263DE057FF398C313D52D5A6C7B4DDE9D985F3BC7D2408D4";
//    	String resp[] = msg.split("\\|");
////    	for(String s: resp) {
////    		System.out.println(s);
////    	}
//    	String orgInput = msg.substring(0, msg.lastIndexOf("|"));
//    	String orgCheckSum = msg.substring(msg.lastIndexOf("|")+1);
//    	String status = "Transaction successfull";
//    	String txnStatus = resp[14];
//    	if(!"0300".equals(txnStatus.trim())) {
//    		status = "Transaction failed";
//    	}
//    	boolean isValidResp = CheckSumGenerator.validateResponse(orgInput, orgCheckSum);
//    	if(!isValidResp) {
//    		status = "Transaction failed";
//    	}
//    	
//    }
}
