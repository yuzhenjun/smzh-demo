package com.server.oauth2.dao;


import java.util.List;

import com.server.oauth2.entity.Client;

/**
 * 
 */
public interface ClientDao {

    public void createClient(Client client);
    public void updateClient(Client client);
    public void deleteClient(Long clientId);

    Client findOne(Long clientId);

    List<Client> findAll();

    Client findByClientId(String clientId);
    Client findByClientSecret(String clientSecret);

}
