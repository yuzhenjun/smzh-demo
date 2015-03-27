package com.server.oauth2.dao;


import java.util.List;

import com.server.oauth2.entity.Client;

/**
 * 
 * @author zhenjun
 *
 */
public interface ClientDao {

    public Client createClient(Client client);
    public Client updateClient(Client client);
    public void deleteClient(Long clientId);

    Client findOne(Long clientId);

    List<Client> findAll();

    Client findByClientId(String clientId);
    Client findByClientSecret(String clientSecret);

}
