package com.webapp.clothes.service;

import com.webapp.clothes.dto.BillAmount;

import java.util.List;

public interface BillDetailService {
    List<Object[]> getSumProductBought(String brand);
}
