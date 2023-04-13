package com.weshopifyapp.features.users.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;
import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;

@Service
public class PersmissionsImpl implements PermissionsService {

	private PermissionsRepository permissionsRepo;
	
	private ModelMapper modelMapper;
	
	public PersmissionsImpl(PermissionsRepository permissionsRepo, ModelMapper modelMapper) {
		this.permissionsRepo = permissionsRepo;
		this.modelMapper = modelMapper;
	} 
	
	@Override
	public PermissionsBean createPermisson(PermissionsBean pbean) throws PermissionsNotFoundException {
		try {
			 return mapEntityToBean(permissionsRepo.save(mapBeanToEntity(pbean)));
		}catch (Exception e) {
			throw PermissionsNotFoundException.builder().message(e.getLocalizedMessage()).build();
		}
		
	}

	@Override
	public PermissionsBean updatePermisson(PermissionsBean pbean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PermissionsBean findPermissonById(int permissionsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PermissionsBean> deletePermissonById(int permissionsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PermissionsBean> getAllPermissons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PermissionsBean> searchPermissons() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Permissions mapBeanToEntity(PermissionsBean permissionsBean) {
		Permissions permissionsEntity =  modelMapper.map(permissionsBean, Permissions.class);
		permissionsEntity.setCreatedBy("admin");
		permissionsEntity.setModifiedBy("admin");
		Date now = new Date();
		permissionsEntity.setCreatedDate(now);
		permissionsEntity.setModifiedDate(now);
		return permissionsEntity;
	}
	
	private PermissionsBean mapEntityToBean(Permissions permissionsEntity) {
		PermissionsBean permissionsBean =  modelMapper.map(permissionsEntity, PermissionsBean.class);
		return permissionsBean;
	}

}
