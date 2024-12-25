package com.cts.SchoolManagementSystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.SchoolManagementSystem.dto.ParentsSaveDTO;
import com.cts.SchoolManagementSystem.entity.Parents;
import com.cts.SchoolManagementSystem.exception.ParentsNotFoundException;
import com.cts.SchoolManagementSystem.repository.ParentRepository;

@Service
public class ParentsService {

    @Autowired
    private ParentRepository parentRepository;

    public List<Parents> findAllParents() throws ParentsNotFoundException {

        List<Parents> parent = new ArrayList<>();
        parent = parentRepository.findAll();
        if(parent.isEmpty())
        {
            throw new ParentsNotFoundException("Parent List is Empty");
        }
        return parent;


    }

    public Parents saveParent(ParentsSaveDTO parentDtoObj) {

        Parents parent = Parents.builder()
                .firstName(parentDtoObj.getFirstName())
                .lastName(parentDtoObj.getLastName())
                .occupation(parentDtoObj.getOccupation())
                .mobileNumber(parentDtoObj.getMobileNumber())
                .address(parentDtoObj.getAddress())
                .build();
        return parentRepository.save(parent);
    }

    public void deleteParentById(Long id) {

        parentRepository.deleteById(id);
    }

    public ArrayList<Parents> findParentByStudentId(Long id) throws ParentsNotFoundException {

        ArrayList<Parents> parent = parentRepository.findParentByStudentId(id);
        if(parent.isEmpty())
        {
            throw new ParentsNotFoundException("Parent List is Empty");
        }
        return parent;
    }

    public Parents updateParent(Parents parent) throws ParentsNotFoundException {

        parentRepository.findById(parent.getParentId()).orElseThrow(()->new ParentsNotFoundException("Parent with parent id "+parent.getParentId()+" is not present in database."));
        return parentRepository.save(parent);
    }

}
