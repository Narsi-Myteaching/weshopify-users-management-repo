package com.weshopifyapp.features.users.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.weshopifyapp.features.users.beans.PermissionsBean;
import com.weshopifyapp.features.users.data.exceptions.PermissionsNotFoundException;
import com.weshopifyapp.features.users.data.models.Permissions;
import com.weshopifyapp.features.users.data.repository.PermissionsRepository;

@Service
public class PermissionsServiceImpl implements PermissionsService {

	private PermissionsRepository permissionsRepo;
	
	private ModelMapper modelMapper;
	
	public PermissionsServiceImpl(PermissionsRepository permissionsRepo, ModelMapper modelMapper) {
		this.permissionsRepo = permissionsRepo;
		this.modelMapper = modelMapper;
	} 
	
	@Override
	public PermissionsBean createPermisson(PermissionsBean pbean) {
		try {
			 return mapEntityToBean(permissionsRepo.save(mapBeanToEntity(pbean)));
		}catch (Exception e) {
			throw PermissionsNotFoundException.builder().message(e.getLocalizedMessage()).build();
		}
		
	}

	@Override
	public PermissionsBean updatePermisson(PermissionsBean pbean) throws PermissionsNotFoundException {
		try {
			 return mapEntityToBean(permissionsRepo.save(mapBeanToEntity(pbean)));
		}catch (Exception e) {
			throw PermissionsNotFoundException.builder().message(e.getLocalizedMessage()).build();
		}
	}

	@Override
	public PermissionsBean findPermissonById(int permissionsId) throws PermissionsNotFoundException {
		Permissions permissionsModel = permissionsRepo.findById(permissionsId)
				                      .orElseThrow(()-> PermissionsNotFoundException
				                    		            .builder()
				                    		            .message("No Permissions Found with the Id:\t"+permissionsId)
				                    		            .build());
		
	
		return mapEntityToBean(permissionsModel);
	}

	@Override
	public List<PermissionsBean> deletePermissonById(int permissionsId) throws PermissionsNotFoundException {
		
		Optional.of(permissionsRepo.existsById(permissionsId)).ifPresentOrElse(id->permissionsRepo.deleteById(permissionsId), 
				      ()->PermissionsNotFoundException.builder().message("No Permissions Found with the Id:\t"+permissionsId).build());
		return getAllPermissons();
	}

	@Override
	public List<PermissionsBean> getAllPermissons() {
		List<PermissionsBean> listOfPermissionBeans = new ArrayList<>();
		Optional.ofNullable(permissionsRepo.findAll()).ifPresentOrElse(listOfPermissions -> {
			listOfPermissions.stream().forEach(permission -> {
				listOfPermissionBeans.add(mapEntityToBean(permission));
			});
		}, () -> PermissionsNotFoundException.builder().message("No Permissions Found in the Database").build());

		return listOfPermissionBeans;
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
		if(permissionsEntity.getPermissionId() >0 && permissionsBean.getPermissionId() == 0) {
			permissionsBean.setPermissionId(permissionsEntity.getPermissionId());
		}
		return permissionsBean;
	}

}
