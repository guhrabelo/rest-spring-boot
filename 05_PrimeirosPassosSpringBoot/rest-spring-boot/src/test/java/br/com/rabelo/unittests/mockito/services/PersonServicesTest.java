package br.com.rabelo.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.rabelo.data.vo.v1.PersonVO;
import br.com.rabelo.exception.RequeiredObjectIsNullException;
import br.com.rabelo.model.Person;
import br.com.rabelo.repository.PersonRepository;
import br.com.rabelo.services.PersonServices;
import br.com.rabelo.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;
	
	@InjectMocks
	private PersonServices service;
	
	@Mock
	PersonRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreate() {
		Person entity = input.mockEntity(1);
		
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
		assertEquals("Addres Test1", result.getAddress());
	}
	
	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequeiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		String expectedMessage = "Não é permitido persistir um objeto nulo!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(list);
		
		var people = service.findAll();
		
		assertNotNull(people);
		assertEquals(14, people.size());
		
		var personTest = people.get(1);
		
		assertNotNull(personTest);
		assertNotNull(personTest.getKey());
		assertNotNull(personTest.getLinks());
		assertTrue(personTest.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", personTest.getFirstName());
		assertEquals("Last Name Test1", personTest.getLastName());
		assertEquals("Female", personTest.getGender());
		assertEquals("Addres Test1", personTest.getAddress());
		
		var personTest4 = people.get(4);
		
		assertNotNull(personTest4);
		assertNotNull(personTest4.getKey());
		assertNotNull(personTest4.getLinks());
		assertTrue(personTest4.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
		assertEquals("First Name Test4", personTest4.getFirstName());
		assertEquals("Last Name Test4", personTest4.getLastName());
		assertEquals("Male", personTest4.getGender());
		assertEquals("Addres Test4", personTest4.getAddress());
		
		var personTest7 = people.get(7);
		
		assertNotNull(personTest7);
		assertNotNull(personTest7.getKey());
		assertNotNull(personTest7.getLinks());
		assertTrue(personTest7.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
		assertEquals("First Name Test7", personTest7.getFirstName());
		assertEquals("Last Name Test7", personTest7.getLastName());
		assertEquals("Female", personTest7.getGender());
		assertEquals("Addres Test7", personTest7.getAddress());
	}

	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
		assertEquals("Addres Test1", result.getAddress());
	}

	@Test
	void testUpdate() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
		assertEquals("Addres Test1", result.getAddress());
	}
	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequeiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		String expectedMessage = "Não é permitido persistir um objeto nulo!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
	}

}
