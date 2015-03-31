package com.server.oauth2.service.oauth2;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.oauth2.dao.ClientDao;
import com.server.oauth2.entity.Client;
import com.server.oauth2.service.impl.ClientService;

/**
 * 
 */
@Transactional
@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientDao clientDao;

	@Override
	public void createClient(Client client) {
		client.setClientId(UUID.randomUUID().toString());
		client.setClientSecret(UUID.randomUUID().toString());
		clientDao.createClient(client);
	}

	@Override
	public void updateClient(Client client) {
		clientDao.updateClient(client);
	}

	@Override
	public void deleteClient(Long clientId) {
		clientDao.deleteClient(clientId);
	}

	@Override
	public Client findOne(Long clientId) {
		return clientDao.findOne(clientId);
	}

	@Override
	public List<Client> findAll() {
		return clientDao.findAll();
	}

	@Override
	public Client findByClientId(String clientId) {
		return clientDao.findByClientId(clientId);
	}

	@Override
	public Client findByClientSecret(String clientSecret) {
		return clientDao.findByClientSecret(clientSecret);
	}
}
