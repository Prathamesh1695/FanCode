package com.example.fancode;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class StepDefinitions {
    
    private List<User> fancodeUsers;

    @Given("Users belong to the city FanCode")
    public void users_belong_to_the_city_fancode() throws IOException {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        List<User> users = apiService.getUsers().execute().body();

        if (users != null) {

            fancodeUsers = users.stream()
                    .filter(user -> {
                        User.Address address = user.getAddress();
                        if (address != null) {
                            User.Geo geo = address.getGeo();
                            if (geo != null) {
                                double lat = Double.parseDouble(geo.getLat());
                                double lng = Double.parseDouble(geo.getLng());
                                return lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100;
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }
    }

    @Then("Each user should have more than 50% of their todos completed")
    public void each_user_should_have_more_than_50_percent_of_their_todos_completed() throws IOException {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        for (User user : fancodeUsers) {
            List<Todo> todos = apiService.getTodosByUserId(user.getId()).execute().body();

            if (todos != null && !todos.isEmpty()) {
                long completedTodos = todos.stream().filter(Todo::isCompleted).count();
                long totalTodos = todos.size();
                
                double completionPercentage = ((double) completedTodos / totalTodos) * 100;
                assertTrue("User " + user.getId() + " has less than 50% completed todos", completionPercentage > 50);
            }
        }
    }
}
