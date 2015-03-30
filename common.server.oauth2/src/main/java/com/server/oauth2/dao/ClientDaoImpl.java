package com.server.oauth2.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.server.oauth2.dao.base.BaseDao;
import com.server.oauth2.entity.Client;

/**
 * 
 */
@Repository
public class ClientDaoImpl extends BaseDao<Client> implements ClientDao {

	@Override
	public void createClient(Client client) {
		this.save(client);
		
	}

	@Override
	public void updateClient(Client client) {
		this.update(client);
	}

	@Override
	public void deleteClient(Long clientId) {
		Client client=this.findById(clientId);
		if(client!=null){
			this.delete(client);
		}
	}

	@Override
	public Client findOne(Long clientId) {
		return this.findById(clientId);
	}

	@Override
	public List<Client> findAll() {
		return this.getAll();
	}

	@Override
	public Client findByClientId(String clientId) {
		String hql="from Client where clientId=?";
		return this.findUnique(hql, clientId);
	}

	@Override
	public Client findByClientSecret(String clientSecret) {
		String hql="from Client where clientSecret=?";
		return this.findUnique(hql, clientSecret);
	}

   
}
