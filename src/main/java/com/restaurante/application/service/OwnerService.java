package com.restaurante.application.service;

import com.restaurante.application.repository.OwnerRepository;
import com.restaurante.domain.Owner;

public class OwnerService {

	private final OwnerRepository ownerRepository;

	public OwnerService(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}

	public Owner crearOwner(Owner owner) {
		return ownerRepository.crearOwner(owner);
	}

	public Owner buscarByEmail(String email) {
		return ownerRepository.buscarByEmail(email);
	}

	public Owner buscarById(Integer id) {
		return ownerRepository.buscarById(id);
	}
}
