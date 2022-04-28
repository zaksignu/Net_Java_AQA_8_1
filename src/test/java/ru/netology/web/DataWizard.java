package ru.netology.web;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.DriverManager;
import java.util.Locale;

public class DataWizard {
    private DataWizard() {}

    static Faker ghostOne = new Faker(new Locale("EN"));

    private static String firstId = null;
    private static String secondId = null;


    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo(FellowOne name) {
        return new AuthInfo(name.getLogin(), name.getHumanPass());
    }
    @Value
    public static class VerificationCode {
        private String code;
    }
    @SneakyThrows
    public static VerificationCode getVerificationCodeFor(FellowOne user) {

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                var cleanUp = conn.createStatement();
        ) {

            try (var rs = cleanUp.executeQuery( "SELECT code from auth_codes WHERE user_id = '"+user.getId()+"';")) {
                if (rs.next()) {
                    return new VerificationCode(rs.getString(1));
                }
            }
        }

        return new VerificationCode("0");
    }

    public static String generateIt() {
        return ghostOne.regexify("[a-z1-9]{10}");
    }


    @SneakyThrows
    public static void userRegister(FellowOne user) {

        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                var registerIt = conn.createStatement();
        ) {
            registerIt.execute("INSERT INTO app.users (id,login,password,status) VALUES('" +
                    user.getId() + "','" +
                    user.getLogin() + "','" +
                    user.getBcCryptPass() + "'," +
                    "'active')");
        }

        //////   123qwerty - $2a$10$vPoHmy/Ku3r0h7WARVkP7eq0RddHgCJRoVmP74hbWpWqWyOAxWOqy
        //////   qwerty123 - $10$Wa3kd.Xw3dR7nFpv4Gjz7ev2PebLT.x9b/7amn.mV5.VsPxZbi3fC

    }

    @SneakyThrows
    public static void makeMeClean() {
        var baseCleanFirstId = "SELECT id FROM app.users WHERE login='vasya';";
        var baseCleanSecondId = "SELECT id FROM app.users WHERE login='petya';";
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
                var cleanUp = conn.createStatement();
        ) {

            try (var rs = cleanUp.executeQuery(baseCleanFirstId)) {
                if (rs.next()) {
                    firstId = rs.getString(1);
                }

            }
            try (var rs = cleanUp.executeQuery(baseCleanSecondId)) {
                if (rs.next()) {
                    secondId = rs.getString(1);
                }
            }
            if (firstId!=null) {
                cleanUp.execute("DELETE FROM app.cards where user_id='" + firstId + "';");
                cleanUp.execute("DELETE FROM app.users where id='" + firstId + "';");

            };
            if (secondId!=null) {
                cleanUp.execute("DELETE FROM app.cards where user_id='" + secondId + "';");
                cleanUp.execute("DELETE FROM app.users where id='" + secondId + "';");
            }
        }

    }



    public static class Registr{
        private Registr() {
        }

        public static FellowOne generateUser() {
            FellowOne user = new FellowOne(
                    generateIt(),
                    generateIt(),
                    "$2a$10$vPoHmy/Ku3r0h7WARVkP7eq0RddHgCJRoVmP74hbWpWqWyOAxWOqy",
                    "123qwerty");
            return user;
        }
    }

    @Value
    public static class FellowOne {
        private String id;
        private String login;
        private String BcCryptPass;
        private String HumanPass;
    }
}
