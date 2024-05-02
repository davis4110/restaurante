package com.restaurante.infrastructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.restaurante.application.repository.OwnerRepository;
import com.restaurante.domain.Owner;
import com.restaurante.infrastructure.entity.OwnerEntity;
import com.restaurante.infrastructure.mapper.OwnerMapper;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository {

	private final OwnerCrudRepository ownerCrudRepository;

	private final OwnerMapper ownerMapper;

	public OwnerRepositoryImpl(OwnerCrudRepository ownerCrudRepository, OwnerMapper ownerMapper) {
		this.ownerCrudRepository = ownerCrudRepository;
		this.ownerMapper = ownerMapper;
	}

	@Override
	public Owner crearOwner(Owner owner) {
		return ownerMapper.toOwner(ownerCrudRepository.save(ownerMapper.toOwnerEntity(owner)));
	}

	@Override
	public Owner buscarByEmail(String email) {
		Optional<OwnerEntity> opOwner = ownerCrudRepository.findByEmail(email);
		return ownerMapper.toOwner((opOwner.isPresent()) ? opOwner.get() : null);
	}

	@Override
	public Owner buscarById(Integer id) {
		Optional<OwnerEntity> opOwner = ownerCrudRepository.findById(id);
		return ownerMapper.toOwner((opOwner.isPresent()) ? opOwner.get() : null);
	}
}
