package com.restaurante.application.repository;

import com.restaurante.domain.Owner;

public interface OwnerRepository {

	public Owner crearOwner(Owner owner);

	public Owner buscarByEmail(String email);

	public Owner buscarById(Integer id);
}
