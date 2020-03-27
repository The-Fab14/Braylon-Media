package com.dev10.BraylonMedia.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author jake
 */
public class OrderMetaInfo {
    
    private int clientId;
    
    private int[] productIds;
    
    private int orderId;

    private LocalDate dateSubmitted;

    private LocalDate dateInstalled;

    private LocalDate dateCompleted;

    private BigDecimal orderTotal;

    private String orderStatus;

    private String orderComments;
}
