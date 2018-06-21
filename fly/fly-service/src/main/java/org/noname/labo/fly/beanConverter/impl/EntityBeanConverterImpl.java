package org.noname.labo.fly.beanConverter.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.noname.labo.fly.beanConverter.EntityBeanConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EntityBeanConverterImpl implements EntityBeanConverter{

	@Autowired
	private Mapper dozerMapper;

	@Override
	public <E, B> B convertToBean(E entity, Class<B> beanClass) {
		if (entity == null) {
			return null;
		}
		B bean = dozerMapper.map(entity, beanClass);

		return bean;
	}

	@Override
	public <E, B> List<B> convertToBeanList(Iterable<E> entities, Class<B> beanClass) {
		if (entities == null) {
			return new ArrayList<>();
		}
		List<B> beans = new ArrayList<>();

		for (E entity : entities) {
			B bean = convertToBean(entity, beanClass);
			beans.add(bean);
		}

		return beans;
	}

	@Override
	public <E, B> E convertToEntity(B bean, Class<E> entityClass) {
		if (bean == null) {
			return null;
		}
		E entity = dozerMapper.map(bean, entityClass);

		return entity;
	}
}
