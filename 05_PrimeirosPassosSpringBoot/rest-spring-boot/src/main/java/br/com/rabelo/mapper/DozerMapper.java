package br.com.rabelo.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <Origem, Destino> Destino parseObject(Origem origin, Class<Destino> destination) {
		return mapper.map(origin, destination);
	}
	
	public static <Origem, Destino> List<Destino> parseListObject(List<Origem> origin, Class<Destino> destination) {
		List<Destino> destinationObjects = new ArrayList<Destino>();
		for (Origem origem : origin) {
			destinationObjects.add(mapper.map(origem, destination));
		}
		return destinationObjects;
	}
}
