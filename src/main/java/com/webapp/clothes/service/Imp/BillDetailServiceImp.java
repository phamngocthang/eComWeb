package com.webapp.clothes.service.Imp;


import com.webapp.clothes.dto.BillAmount;
import com.webapp.clothes.repositories.BillDetailRepository;
import com.webapp.clothes.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BillDetailServiceImp implements BillDetailService {

    @Autowired
    BillDetailRepository billDetailRepository;
    @Override
    public List<Object[]> getSumProductBought(String brand) {
        return billDetailRepository.getSumProductBought(brand);
    }
}
