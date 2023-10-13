package br.com.rabelo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rabelo.data.vo.v1.PersonVO;
import br.com.rabelo.exception.ResourceNotFoundException;
import br.com.rabelo.mapper.DozerMapper;
import br.com.rabelo.model.Person;
import br.com.rabelo.repository.PersonRepository;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;
    

    public PersonVO create(PersonVO person) {
    	var entity = DozerMapper.parseObject(person, Person.class);
    	var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }
    
    public List<PersonVO> findAll() {
        return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}