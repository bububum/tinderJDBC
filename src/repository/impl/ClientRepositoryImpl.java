package repository.impl;

import model.entitiy.Client;
import model.entitiy.Order;
import model.enums.*;
import repository.ClientRepository;
import repository.DbHelper;
import service.ClientService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryImpl implements ClientRepository {

    private DbHelper dbHelper = new DbHelperImpl();
    @Override
    public void save(Client client) {
        try(PreparedStatement preparedStatement = dbHelper.getPreparedStatement(
                "INSERT INTO client (status, created_date, updated_date, email, full_name, age, gender, family_status, country, city, about, zodiac, birth_date) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, client.getStatus().toString());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(client.getCreatedDate()));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(client.getUpdatedDate()));
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getFullName());
            preparedStatement.setInt(6,client.getAge());
            preparedStatement.setString(7, client.getGender().toString());
            preparedStatement.setString(8, client.getFamilyStatus().toString());
            preparedStatement.setString(9, client.getCountry().toString());
            preparedStatement.setString(10, client.getCity());
            preparedStatement.setString(11, client.getAbout());
            preparedStatement.setString(12, client.getZodiac().toString());
            preparedStatement.setTimestamp(13, Timestamp.valueOf(client.getBirthDate().atStartOfDay()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public Client findById(Long id) {
        Client client = null;
        try (PreparedStatement preparedStatement = dbHelper.getPreparedStatement(
                "SELECT * FROM tb_client WHERE id = ?")) {

            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setStatus(resultSet.getString("status");
                client.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                client.setUpdatedDate(resultSet.getTimestamp("updated_date").toLocalDateTime());
                client.setEmail(resultSet.getString("email"));
                client.setFullName(resultSet.getString("full_name"));
                client.setAge(resultSet.getInt("age"));
                client.setGender(resultSet.getString("gender");
                client.setFamilyStatus(resultSet.getString("familyStatus");
                client.setCountry(
                client.setCity(resultSet.getString("city"));
                client.setAbout(resultSet.getString("about"));
                client.setZodiac(
                client.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
            }
        } catch (SQLException e) {
        }
        return client;
    }

        @Override
    public List<Client> findAll() {
        List<Client> clientList = new ArrayList<>();
        try(PreparedStatement preparedStatement = dbHelper.getPreparedStatement(
                "SELECT * FROM tb_client")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setStatus(ClientStatus.valueOf(resultSet.getString("status")));
                client.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                client.setUpdatedDate(resultSet.getTimestamp("updated_date").toLocalDateTime());
                client.setEmail(resultSet.getString("email"));
                client.setFullName(resultSet.getString("full_name"));
                client.setAge(resultSet.getInt("age"));
                client.setGender(Gender.valueOf(resultSet.getString("gender")));
                client.setFamilyStatus(FamilyStatus.valueOf(resultSet.getString("family_status")));
                client.setCountry(Country.valueOf(resultSet.getString("country")));
                client.setCity(resultSet.getString("city"));
                client.setAbout(resultSet.getString("about"));
                client.setZodiac(Zodiac.valueOf(resultSet.getString("zodiac")));
                client.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                clientList.add(client);
            }
        } catch (SQLException e) {
        }
        return clientList;
    }

    @Override
    public void update(Client client) {
        try(PreparedStatement preparedStatement = dbHelper.getPreparedStatement(
                "UPDATE tb_client SET " +
                        "status = ?, " +
                        "updated_date = ?, " +
                        "email = ?, " +
                        "full_name = ?, " +
                        "age = ?, " +
                        "gender = ?, " +
                        "family_status = ?, " +
                        "country = ?, " +
                        "city = ?, " +
                        "about = ?, " +
                        "zodiac = ?, " +
                        "birth_date = ? " +
                        "WHERE id = ?")) {

            preparedStatement.setString(1, client.getStatus().toString());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(client.getUpdatedDate()));
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getFullName());
            preparedStatement.setInt(6, client.getAge());
            preparedStatement.setString(7, client.getGender().toString());
            preparedStatement.setString(8, client.getFamilyStatus().toString());
            preparedStatement.setString(9, client.getCountry().toString());
            preparedStatement.setString(10, client.getCity());
            preparedStatement.setString(11, client.getAbout());
            preparedStatement.setString(12, client.getZodiac().toString());
            preparedStatement.setDate(13, java.sql.Date.valueOf(client.getBirthDate()));

            preparedStatement.setLong(14, client.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
        }

    }

    @Override
    public void deleteById(Long id) {
        Client client = findById(id);
        client.setStatus(ClientStatus.DELETED);

    }

    @Override
    public List<Client> findMatchOrders(Long id) {

        List<Client> clientList = new ArrayList<>();

        try(PreparedStatement preparedStatement = dbHelper.getPreparedStatement(
                "SELECT * FROM tb_client c INNER JOIN tb_order o ON c.id = o.sender_id OR c.id = o.recipient_id WHERE (sender_id = ? OR recipient_id = ?) AND status = 'ACCEPTED'")) {
            preparedStatement.setLong(1,id);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setStatus(ClientStatus.valueOf(resultSet.getString("status")));
                client.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
                client.setUpdatedDate(resultSet.getTimestamp("updated_date").toLocalDateTime());
                client.setEmail(resultSet.getString("email"));
                client.setFullName(resultSet.getString("full_name"));
                client.setAge(resultSet.getInt("age"));
                client.setGender(Gender.valueOf(resultSet.getString("gender")));
                client.setFamilyStatus(FamilyStatus.valueOf(resultSet.getString("family_status")));
                client.setCountry(Country.valueOf(resultSet.getString("country")));
                client.setCity(resultSet.getString("city"));
                client.setAbout(resultSet.getString("about"));
                client.setZodiac(Zodiac.valueOf(resultSet.getString("zodiac")));
                client.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                clientList.add(client);
            }

        } catch (SQLException e) {
        }
        return clientList;
    }
}
