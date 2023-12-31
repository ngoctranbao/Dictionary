package mycutedict.backend;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static final String URL = "jdbc:sqlite:src/main/resources/mycutedict/DataFile/dict_hh.db";
    private Connection connection;
    ArrayList<Word> dictionary = new ArrayList<>();

    public Database() {
        try {
            connection = DriverManager.getConnection(URL);
            getAllTable();
            if (dictionary.isEmpty()) {
                System.out.println("can't get database");
            }
        } catch (SQLException e) {
            System.out.println("error constructor");
        }
    }
    /**
     * Disconnect with MySQL.
     *
     * @param connection
     */
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Cannot execute closing connection.");
            e.printStackTrace();
        }
    }

    /**
     * close preparedstatement.
     *
     * @param preparedStatement
     */
    public static void close(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            System.out.println("Cannot close preparedStatement.");
            e.printStackTrace();
        }
    }

    /**
     * close resultSet.
     *
     * @param resultSet
     */
    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("Cannot close resultSet.");
            e.printStackTrace();
        }
    }

    /**
     * close database connection.
     */
    public void close() {
        close(connection);
    }


    /**
     * Connect to database.
     *
     */
    public void setConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        System.out.println("Connected to database.");
    }

    /**
     * get just column targetWord from database.
     */
    public ArrayList<String> getTargetWords() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM av");
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    ArrayList<String> targetWords = new ArrayList<>();
                    while (resultSet.next()) {
                        targetWords.add(resultSet.getString("targetWord"));
                    }
                    return targetWords;
                } finally {
                    close(resultSet);
                }
            } finally {
                close(preparedStatement);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }


    public void getAllTable() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM av");
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                try {
                    while (resultSet.next()) {
                        String word_target = resultSet.getString("wordTarget");
                        String word_explain = resultSet.getString("wordExplain");
                        String type = resultSet.getString("type");
                        String ipa = resultSet.getString("ipa");
                        Word word = new Word(word_target, word_explain, ipa, type);
                        dictionary.add(word);
                    }
                } finally {
                    close(resultSet);
                }
            } finally {
                close(preparedStatement);
            }
        } catch (SQLException e) {
            System.out.println("return null error");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Word> getDatabase() {
        return dictionary;
    }

    public void importFromFile(String name, ArrayList<Integer> dic) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(name));
            String line;
            while ((line = reader.readLine()) != null) {
                if(!line.isEmpty()) {
                    Integer integer = Integer.valueOf(line);
                    dic.add(integer);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("wait ...");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportToFile(String name, ArrayList<Integer> dic) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(name));
            int cnt = 0;
            while (cnt < dic.size()) {
                String temp = Integer.toString(dic.get(cnt));
                temp += "\n";
                writer.write(temp);
                cnt++;
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("ouch, we have a bug.");
        }
    }
}
