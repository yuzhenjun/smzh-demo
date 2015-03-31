package com.server.oauth2.service.impl;



import java.util.List;

import com.server.oauth2.entity.Client;

/**
 * 
 */
public interface ClientService {

    public void createClient(Client client);
    public void updateClient(Client client);
    public void deleteClient(Long clientId);

    Client findOne(Long clientId);

    List<Client> findAll();

    Client findByClientId(String clientId);
    Client findByClientSecret(String clientSecret);

}
