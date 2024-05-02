package com.restaurante.application.service;

import com.restaurante.application.repository.OwnerRepository;
import com.restaurante.domain.Owner;

public class OwnerService {

	private final OwnerRepository ownerRepository;

	public OwnerService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	public Owner createOwner(Owner owner) {
		return ownerRepository.createOwner(owner);
	}

	public Owner findByEmail(String email) {
		return ownerRepository.findByEmail(email);
	}

	public Owner findById(Integer id) {
		return ownerRepository.findById(id);
	}
}
