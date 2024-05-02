package com.restaurante.application.repository;

import com.restaurante.domain.Owner;

public interface OwnerRepository {

	public Owner createOwner(Owner owner);

	public Owner findByEmail(String email);

	public Owner findById(Integer id);
}
