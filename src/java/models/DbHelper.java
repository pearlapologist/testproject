/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 *
 * @author bayan
 */
public class DbHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "1q2w3e4r";

    public static final String TABLE_PERSON = "person";
    public static final String TABLE_ORDERS = "orders";
    public static final String TABLE_ORDERNPERSON = "order_person";
    public static final String TABLE_EXECUTOR = "executor";
    public static final String TABLE_EXECUTORS = "executors";
    public static final String TABLE_SERVICE = "service";
    //public static final String TABLE_SERVICES = "services";
    public static final String TABLE_SECTION = "section";
    // public static final String TABLE_SECTIONS = "sections";
    public static final String TABLE_BOOKMARKS = "bookmarks";
    public static final String TABLE_REVIEWS = "reviews";
    public static final String TABLE_EXECUTORNSERVICES = "executor_services";
    public static final String TABLE_NOTIFY = "ntfctns";
    public static final String TABLE_EXECUTORNPERSON = "executor_person";
    public static final String TABLE_RESPONSE = "responses";

    public static final String KEY_PERSON_ID = "_idPerson";
    public static final String KEY_ORDER_ID = "_idOrder";
    public static final String KEY_EXECUTOR_ID = "idExecutor";
    public static final String KEY_SERVICE_ID = "_idService";
    public static final String KEY_SECTION_ID = "_idSection";
    public static final String KEY_EXECUTORNSERVICES_ID = "_idExecnserv";
    public static final String KEY_NOTIFY_ID = "_idNtf";
    public static final String KEY_ORDERNPERSON_PART_ID = "_idOrdnper";
    public static final String KEY_EXECUTORNPERSON_ID = "_idExenper";
    public static final String KEY_RESPONSE_ID = "_idResponses";

    public static final String KEY_PERSON_NAME = "person_name";
    public static final String KEY_PERSON_LASTNAME = "person_lastname";
    public static final String KEY_PERSON_PASSWD = "person_passwd";
    public static final String KEY_PERSON_CREATED_DATE = "person_created_date";
    public static final String KEY_PERSON_NUMBER = "person_number";
    public static final String KEY_PERSON_PHOTO = "photo";
    public static final String KEY_PERSON_RATING = "person_rating";

    public static final String KEY_ORDER_TITLE = "order_title";
    public static final String KEY_ORDER_SECTION_ID = "order_section_id";
    public static final String KEY_ORDER_PRICE = "order_price";
    public static final String KEY_ORDER_CREATED_DATE = "order_created_date";
    public static final String KEY_ORDER_DEADLINE = "order_deadline";
    public static final String KEY_ORDER_DESCRIPTION = "order_description";
//    public static final String KEY_ORDER_EXECUTOR_ID = "executor_id";
//    public static final String KEY_ORDER_CUSTOMER_ID = "customer_id";

    public static final String KEY_EXECUTOR_PERSON_ID = "executor_personId";
    public static final String KEY_EXECUTOR_SECTION_ID = "executor_sectionId";
    public static final String KEY_EXECUTOR_SPECIALIZATION = "executor_specialization";
    public static final String KEY_EXECUTOR_DESCRIPTION = "executor_description";

    // public static final String KEY_SERVICE_PERSON_ID = "person_id";
    public static final String KEY_SERVICE_TITLE = "service_title";
    public static final String KEY_SERVICE_PRICE = "service_price";

    public static final String KEY_SECTION_TITLE = "section_title";

    public static final String KEY_BOOKMARK_PART_ID = "_idBkm";
    public static final String KEY_BOOKMARK_ORDER_ID = "bkm_order_id";
    public static final String KEY_BOOKMARK_PERSON_ID = "bkm_person_id";
    public static final String KEY_BOOKMARK_EXECUTOR_ID = "bkm_executor_id";

    public static final String KEY_ORDERNPERSON_CUSTOMER_ID = "ordnper_customer_id";
    public static final String KEY_ORDERNPERSON_ORDER_ID = "ordnper_order_id";
    public static final String KEY_ORDERNPERSON_EXECUTOR_ID = "ordnper_executor_id";
    public static final String KEY_ORDERNPERSON_STATUS = "ordnper_status";

    public static final String KEY_REVIEW_PART_ID = "_idReview";
    public static final String KEY_REVIEW_EXECUTOR_ID = "rev_executor_id";
    public static final String KEY_REVIEW_CUSTOMER_ID = "rev_customer_id";
    public static final String KEY_REVIEW_ORDER_ID = "rev_order_id";
    public static final String KEY_REVIEW_REVIEW_TEXT = "review_text";
    public static final String KEY_REVIEW_ASSESSMENT = "assessment";

    public static final String KEY_LIST_OF_EXECUTORS_PART_ID = "_id";
    public static final String KEY_LIST_OF_EXECUTOR_ID = "excutor_id";
    public static final String KEY_LIST_OF_EXECUTORS_SPECIALIZATION = "specialization";

    public static final String KEY_EXECUTORNSERVICES_EXECUTOR_ID = "execnser_executor_id";
    public static final String KEY_EXECUTONSERVICES_SERVICE_ID = "execnserv_service_id";

    public static final String KEY_NOTIFY_PERSON_ID = "ntf_personId";
    public static final String KEY_NOTIFY_TEXT = "ntf_text";
    public static final String KEY_NOTIFY_CREATED_DATE = "ntf_created_date";

    public static final String KEY_EXECUTORNPERSON_EXECUTOR_ID = "execnper_executorId";
    public static final String KEY_EXECUTORNPERSON_PERSON_ID = "execnper_personId";

    public static final String KEY_RESPONSE_ORDER_ID = "resp_orderId";
    public static final String KEY_RESPONSE_PERSON_ID = "resp_personId";
    public static final String KEY_RESPONSE_PRICE = "resp_price";
    public static final String KEY_RESPONSE_TEXT = "resp_text";

    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    //TODO: WRITE ALL CRUD METHODS FOR DATABASE
    public ArrayList<Question> getAllQuestions() {
        String query = "select * from questions";

        ArrayList<Question> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("idQuestn");
                String title = rs.getString("title");
                String ans1 = rs.getString("opt1");
                String ans2 = rs.getString("opt2");
                String ans3 = rs.getString("opt3");
                String ans4 = rs.getString("opt4");
                int correct = rs.getInt("correct");
                Question t = new Question(title, ans1, ans2, ans3, ans4, correct);
                t.setId(id);
                result.add(t);
            }
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }

    public void addQuestion(Question question) {
        String query = "INSERT INTO questions "
                       + "(title, opt1,opt2,opt3,opt4,correct)"
                       + " VALUES(?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            con.setAutoCommit(false);
            PreparedStatement pstmt
                              = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getOpt1());
            pstmt.setString(3, question.getOpt2());
            pstmt.setString(4, question.getOpt3());
            pstmt.setString(5, question.getOpt4());
            pstmt.setInt(6, question.getCorrect());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            int candidateId = 0;
            if (rs.next()) {
                candidateId = rs.getInt(1);
                question.setId(candidateId);
            }
            con.commit();
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception ex) {
            try {
                con.rollback();
            } catch (Exception ex2) {
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Person">
    public void addPerson(Person person) {
        String query = "INSERT INTO " + TABLE_PERSON + "(" + KEY_PERSON_NAME + ", " + KEY_PERSON_LASTNAME
                       + ", " + KEY_PERSON_PASSWD + ", " + KEY_PERSON_NUMBER + ", " + KEY_PERSON_RATING + ", " + KEY_PERSON_CREATED_DATE + "," + KEY_PERSON_PHOTO
                       + ") VALUES (?, ?, ?, ?, ?, ?, ? )";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getLastname());
            pstmt.setString(3, person.getPasswd());
            pstmt.setString(4, person.getNumber());
            pstmt.setInt(5, person.getRating());
            pstmt.setLong(6, person.getCreatedDate());
            pstmt.setString(7, person.getPhoto());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.first()) {
                person.setId(1);
            }

            con.commit();
            pstmt.close();
            rs.close();
        } catch (Exception ex) {

            try {
                con.rollback();
            } catch (Exception exception2) {
            }
        }
    }

    public Person getPerson(int personId) {

        String query = "select * from " + TABLE_PERSON
                       + " where " + KEY_PERSON_ID + "=" + personId;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                Person p = this.getPersonRs(rs);
                return p;
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }

        return null;
    }

    public void updatePerson(Person person) {
        String query = "UPDATE " + TABLE_PERSON + " SET "
                       + KEY_PERSON_NAME + "=?,"
                       + KEY_PERSON_LASTNAME + "=?,"
                       + KEY_PERSON_PASSWD + "=?,"
                       + KEY_PERSON_RATING + "=?,"
                       + KEY_PERSON_NUMBER + "=?,"
                       + KEY_PERSON_CREATED_DATE + "=?,"
                       + KEY_PERSON_PHOTO + "=?"
                       + " WHERE " + KEY_PERSON_ID + "=" + person.getId();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement(query, Statement.NO_GENERATED_KEYS);

            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getLastname());
            pstmt.setString(3, person.getPasswd());
            pstmt.setInt(4, person.getRating());
            pstmt.setString(5, person.getNumber());
            pstmt.setLong(6, person.getCreatedDate());
            pstmt.setString(7, person.getPhoto());

            pstmt.executeUpdate();

            con.commit();
            pstmt.close();

            con.close();
        } catch (Exception ex) {

            try {
                con.rollback();
            } catch (Exception exception2) {
            }
        }
    }

    public void deletePerson(int personId) {
        String query = "DELETE * FROM " + TABLE_PERSON + " WHERE "
                       + KEY_PERSON_ID + "=" + personId;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public ArrayList<Person> getPersons() {
        String query = "select * from "
                       + TABLE_PERSON + " order by " + KEY_PERSON_ID
                       + " desc";

        ArrayList<Person> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Person person = this.getPersonRs(rs);
                result.add(person);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }

    private Person getPersonRs(ResultSet rs) throws Exception {
        int id = rs.getInt(KEY_PERSON_ID);
        String name = rs.getString(KEY_PERSON_NAME);
        String last = rs.getString(KEY_PERSON_LASTNAME);
        String number = rs.getString(KEY_PERSON_NUMBER);
        String passwd = rs.getString(KEY_PERSON_PASSWD);
        int rating = rs.getInt(KEY_PERSON_RATING);
        long date = rs.getLong(KEY_PERSON_CREATED_DATE);
        String photo = rs.getString(KEY_PERSON_PHOTO);

        Person p = new Person(id, name, last, passwd, number, rating, date);
        p.setPhoto(photo);
        return p;
    }

    /**
     * Возвращает пользователя по номеру, чтобы проверить зарегистрирован ли он
     * уже
     */
    public Person getPersonByNumb(String number) {
        String query = "select * from "
                       + TABLE_PERSON + " where" + KEY_PERSON_NUMBER + "='"
                       + number + "'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                Person p = getPersonRs(rs);
                return p;
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }

    /**
     * Возвращает пользователя по номеру и паролю для авторизации
     */
    public Person getPersonNP(String numb, String passwd) {
        String query = "select * from person  where person_number ='" + numb + " ' AND person_passwd= '" + passwd + "'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                Person p = getPersonRs(rs);
                return p;
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> getPersonsId() {
        String query = "SELECT " + KEY_PERSON_ID + " FROM " + TABLE_PERSON + " order by " + KEY_PERSON_ID
                       + " desc";

        ArrayList<Integer> arrID = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                arrID.add(rs.getInt(KEY_PERSON_ID));
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return arrID;
    }

  
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Executor">
    public void addExecutor(Executor executor) {
        String query = "INSERT INTO " + TABLE_EXECUTOR + "(" + KEY_EXECUTOR_PERSON_ID
                       + ", " + KEY_EXECUTOR_SECTION_ID + ", " + KEY_EXECUTOR_SPECIALIZATION
                       + ", " + KEY_EXECUTOR_DESCRIPTION + ") VALUES (?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            con.setAutoCommit(false);
            PreparedStatement pstmt
                              = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, executor.getPersonId());
            pstmt.setInt(2, executor.getSectionId());
            pstmt.setString(3, executor.getSpecialztn());
            pstmt.setString(4, executor.getDescriptn());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
                executor.setId(id);
            }
            con.commit();
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception ex) {
            try {
                con.rollback();
            } catch (Exception ex2) {
            }
        }
    }

    private Executor getExecutorFromRS(ResultSet set) throws Exception {

        int id = set.getInt(KEY_EXECUTOR_ID);
        int personId = set.getInt(KEY_EXECUTOR_PERSON_ID);
        int sectionId = set.getInt(KEY_EXECUTOR_SECTION_ID);
        String spcltn = set.getString(KEY_EXECUTOR_SPECIALIZATION);
        String dscrp = set.getString(KEY_EXECUTOR_DESCRIPTION);

        Executor executor = new Executor(id, personId, sectionId, spcltn, dscrp);
        return executor;
    }

    public Executor getExecutor(int executorId) {
        String query = "select * from " + TABLE_EXECUTOR + " where "
                       + KEY_EXECUTOR_ID + "=" + executorId;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                Executor executor = this.getExecutorFromRS(rs);
                return executor;
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }

    public ArrayList<Executor> getExecutors() {
        String query = "select * from "
                       + TABLE_EXECUTOR + " order by " + KEY_EXECUTOR_ID
                       + " desc";

        ArrayList<Executor> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Executor executor = this.getExecutorFromRS(rs);
                result.add(executor);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }
    
    
    public int getExecutorsCount() {
        String query = "select count(*) from " + TABLE_EXECUTOR;
        try {
            Connection con = getCurrConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                int count = rs.getInt(1);
                con.close();
                stmt.close();
                rs.close();
                return count;
            }

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return -1;
    }
    
      public ArrayList<Executor> getExecutorsRecords(int start, int total) {
        ArrayList<Executor> list = new ArrayList<>();
        try {
            Connection connection = getCurrConnection();
            int i = total * (start - 1);
            PreparedStatement ps = connection.prepareStatement("SELECT * from " + TABLE_EXECUTOR + " limit "
                                                               + i + "," + total);

            rs = ps.executeQuery();

            while (rs.next()) {
                Executor executor = getExecutorFromRS(rs);
                list.add(executor);
            }
            connection.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return list;
    }

    public void updateExecutor(Executor executor) {
        String query = "UPDATE " + TABLE_EXECUTOR + " SET "
                       + KEY_EXECUTOR_SPECIALIZATION + "='" + executor.getSpecialztn() + "', "
                       + KEY_EXECUTOR_DESCRIPTION + "=' " + executor.getDescriptn() + "', "
                       + KEY_EXECUTOR_PERSON_ID + "= " + executor.getPersonId() + ", "
                       + KEY_EXECUTOR_SECTION_ID + "= " + executor.getSectionId()
                       + " WHERE " + KEY_EXECUTOR_ID + "=" + executor.getId();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            // query = "SELECT MAX(id) FROM users";
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public void deleteExecutor(int executorId) {
        String query = "DELETE * FROM " + TABLE_EXECUTOR + " WHERE "
                       + KEY_EXECUTOR_ID + "=" + executorId;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }
    //</editor-fold>

    // <editor-fold desc="Order" defaultstate="collapsed">
    public void addOrder(Order order) {
        String query = "INSERT INTO " + TABLE_ORDERS + "(" + KEY_ORDER_TITLE
                       + ", " + KEY_ORDER_SECTION_ID + ", " + KEY_ORDER_PRICE + ", "
                       + KEY_ORDER_DESCRIPTION + ", " + KEY_ORDER_DEADLINE + ", " + KEY_ORDER_CREATED_DATE
                       + ") VALUES (?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            con.setAutoCommit(false);
            PreparedStatement pstmt
                              = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, order.getTitle());
            pstmt.setInt(2, order.getSection());
            pstmt.setDouble(3, order.getPrice());
            pstmt.setString(4, order.getDescription());
            pstmt.setLong(5, order.getDeadline());
            pstmt.setLong(6, DataUtils.getCurentDateInLong());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
                order.setId(id);
            }
            con.commit();
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception ex) {
            try {
                con.rollback();
            } catch (Exception ex2) {
            }
        }
    }

    private Order getOrderFromRS(ResultSet set) throws Exception {

        int id = set.getInt(KEY_ORDER_ID);
        String title = set.getString(KEY_ORDER_TITLE);
        int sectionId = set.getInt(KEY_ORDER_SECTION_ID);
        double price = set.getDouble(KEY_ORDER_PRICE);
        String dscrp = set.getString(KEY_ORDER_DESCRIPTION);
        Long deadline = set.getLong(KEY_ORDER_DEADLINE);
        Long createdDate = set.getLong(KEY_ORDER_CREATED_DATE);

        Order order = new Order(id, title, sectionId, price, dscrp, deadline, createdDate);
        return order;
    }

    public Order getOrder(int orderId) {
        String query = "select * from "
                       + TABLE_ORDERS + " where " + KEY_ORDER_ID + "=" + orderId;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                Order order = this.getOrderFromRS(rs);
                return order;
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        String query = "select * from "
                       + TABLE_ORDERS + " order by " + KEY_ORDER_ID
                       + " desc";

        ArrayList<Order> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Order order = getOrderFromRS(rs);
                //loadExecutorServices(executor);
                result.add(order);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }

    public ArrayList<Order> getOrdersRecords(int start, int total) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection connection = getCurrConnection();
            int i = total * (start - 1);
            // int i = total * start;
            PreparedStatement ps = connection.prepareStatement("SELECT * from " + TABLE_ORDERS + " limit "
                                                               + i + "," + total);

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = getOrderFromRS(rs);
                list.add(order);
            }
            connection.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return list;
    }

    public int getOrderCount() {
        String query = "select count(*) from " + TABLE_ORDERS;
        try {
            Connection con = getCurrConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                int count = rs.getInt(1);
                con.close();
                stmt.close();
                rs.close();
                return count;
            }

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return -1;
    }

    public Connection getCurrConnection() {
        Connection con2 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public ArrayList<Order> getOrders(Order_search search) {
        if (search == null) {
            search = new Order_search();
        }

        ArrayList<Order> result = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_ORDERS + " WHERE 1=1";
        if (search.getPersonId() != 0) {
            sql += " AND (" + KEY_ORDERNPERSON_CUSTOMER_ID + "=" + search.getPersonId()
                   + " OR " + KEY_ORDERNPERSON_EXECUTOR_ID + "=" + search.getPersonId() + ")";
        }
        else if (search.getPersonId1ThatISend() != 0) {
            sql += " AND " + KEY_ORDERNPERSON_CUSTOMER_ID + "=" + search.getPersonId1ThatISend();
        }
        else if (search.getPersonId2ThatIAccept() != 0) {
            sql += " AND " + KEY_ORDERNPERSON_EXECUTOR_ID + "=" + search.getPersonId2ThatIAccept();
        }
        if (search.getStatuses().isEmpty()) {
            String statuses = "";
            for (String s : search.getStatuses()) {
                statuses += "'" + s + "', ";
            }
            if (statuses.endsWith(", ")) {
                statuses = statuses.substring(0, statuses.length() - 2);
            }
            sql += " AND " + KEY_ORDERNPERSON_STATUS + " IN(" + statuses + ")";
        }
        sql += " ORDER BY " + KEY_ORDERNPERSON_PART_ID + " DESC";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Order order = getOrderFromRS(rs);
                //loadExecutorServices(executor);
                result.add(order);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }

    public void updateOrder(Order order) {
        String query = "UPDATE " + TABLE_ORDERS + " SET "
                       + KEY_ORDER_TITLE + "='" + order.getTitle() + "', "
                       + KEY_ORDER_SECTION_ID + "=' " + order.getSection() + "', "
                       + KEY_ORDER_PRICE + "= " + order.getPrice() + ", "
                       + KEY_ORDER_DESCRIPTION + "= '" + order.getDescription() + "', "
                       + KEY_ORDER_DEADLINE + "= " + order.getDeadline()
                       + " WHERE " + KEY_ORDER_ID + " = " + order.getId();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public void deleteOrder(int orderId) {
        String query = "DELETE * FROM " + TABLE_ORDERS + " WHERE "
                       + KEY_ORDER_ID + "=" + orderId;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }
    // </editor-fold>

    //<editor-fold desc="Service">
    public void addService(Service service) {
        String query = "INSERT INTO " + TABLE_SERVICE + "(" + KEY_SERVICE_TITLE + ", "
                       + KEY_SERVICE_PRICE + ") VALUES ( ?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            con.setAutoCommit(false);
            PreparedStatement pstmt
                              = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, service.getTitle());
            pstmt.setDouble(2, service.getPrice());

            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
                service.setId(id);
            }
            con.commit();
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception ex) {
            try {
                con.rollback();
            } catch (Exception ex2) {
            }
        }
    }

    public Service getService(int serviceId) {

        String query = "select * from "
                       + TABLE_SERVICE + " where " + KEY_SERVICE_ID + " = " + serviceId;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                Service s = this.getServiceFromRS(rs);
                return s;
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }

        return null;
    }

    public ArrayList<Service> getServices() {
        String query = "select * from "
                       + TABLE_SERVICE + " order by " + KEY_SERVICE_ID + " desc";

        ArrayList<Service> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Service s = this.getServiceFromRS(rs);
                result.add(s);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }

    public void loadExecutorServices(Executor executor) {

        String sql = "SELECT " + TABLE_SERVICE + "." + KEY_SERVICE_ID + ", "
                     + TABLE_SERVICE + "." + KEY_SERVICE_TITLE + ", " + TABLE_SERVICE + "." + KEY_SERVICE_PRICE
                     + " FROM "
                     + TABLE_SERVICE + " JOIN " + TABLE_EXECUTORNSERVICES + " ON "
                     + TABLE_SERVICE + "." + KEY_SERVICE_ID + "=" + TABLE_EXECUTORNSERVICES
                     + "." + KEY_EXECUTONSERVICES_SERVICE_ID + " WHERE " + TABLE_EXECUTORNSERVICES
                     + "." + KEY_EXECUTORNSERVICES_EXECUTOR_ID + "=" + executor.getId();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            //SELECT distinct Service.ed, service.title, service.price from service
            /*join executernservices
         on service._id = executernservices.serviceid
         where executernservices.executorid = executorid
             */
            if (executor.getServices() != null && !(executor.getServices().isEmpty())) {
                executor.getServices().clear();
            }
            while (rs.next()) {
//            String serviceTitle = c2.getString(1);
//            int serviceId = c2.getInt(2);
//            Service service = new Service();
//            service.setId(serviceId);
                //service.setTitle(serviceTitle);
                Service service = new Service(rs.getInt(KEY_SERVICE_ID), rs.getString(KEY_SERVICE_TITLE), rs.getDouble(KEY_SERVICE_PRICE));
                executor.getServices().add(service);
            }
            con.close();
            stmt.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }

    }

    public void updateService(Service service) {
        String query = "UPDATE " + TABLE_SERVICE + " SET "
                       + KEY_SERVICE_TITLE + "='" + service.getTitle() + "', "
                       + KEY_SERVICE_PRICE + "=" + service.getPrice()
                       + " WHERE " + KEY_SERVICE_ID + " = " + service.getId();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public void deleteService(int serviceId) {
        String query = "DELETE * FROM " + TABLE_SERVICE + " WHERE "
                       + KEY_SERVICE_ID + "=" + serviceId;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    private Service getServiceFromRS(ResultSet rs) throws Exception {
        int id = rs.getInt(KEY_SERVICE_ID);
        String title = rs.getString(KEY_SERVICE_TITLE);
        double price = rs.getDouble(KEY_SERVICE_PRICE);
        Service service = new Service(id, title, price);
        return service;
    }

    //</editor-fold>
    //<editor-fold desc="EXECUTORNSERVICES">
    public void addExecutorNServices(int executorId, int serviceId) {

        String query = "INSERT INTO " + TABLE_EXECUTORNSERVICES + "(" + KEY_EXECUTORNSERVICES_EXECUTOR_ID + ", "
                       + KEY_EXECUTONSERVICES_SERVICE_ID + ") VALUES ( " + executorId + "," + serviceId + " )";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public void updateExecutorNServices(Executor executor) {
        if (executor.getServices() == null || executor.getId() == 0) {
            return;
        }

        String sql = "DELETE FROM " + TABLE_EXECUTORNSERVICES
                     + " WHERE " + KEY_EXECUTORNSERVICES_EXECUTOR_ID + "=" + executor.getId();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            stmt.execute(sql);

            //SELECT distinct Service.ed, service.title, service.price from service
            /*join executernservices
         on service._id = executernservices.serviceid
         where executernservices.executorid = executorid
             */
            for (Service service : executor.getServices()) {
                sql = "INSERT INTO " + TABLE_EXECUTORNSERVICES + "("
                      + KEY_EXECUTORNSERVICES_EXECUTOR_ID + ", " + KEY_EXECUTONSERVICES_SERVICE_ID
                      + ") VALUES (" + executor.getId() + ", " + service.getId() + ")";
                stmt.execute(sql);
            }
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    //</editor-fold>
    //<editor-fold desc="Section">
    public void addSection(Section section) {
        String query = "INSERT INTO " + TABLE_SECTION + "(" + KEY_SECTION_TITLE + ") VALUES ( '"
                       + section.getTitle() + ")";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public Section getSection(int sectionId) {

        String query = "select * from "
                       + TABLE_SECTION + " where " + KEY_SECTION_ID + "=" + sectionId;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                int id = rs.getInt(KEY_SECTION_ID);
                String title = rs.getString(KEY_SECTION_TITLE);
                Section section = new Section(id, title);
                return section;
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }

        return null;
    }

    public void updateSection(Section c) {
        String query = "UPDATE " + TABLE_SECTION + " SET "
                       + KEY_SECTION_TITLE + "='" + c.getTitle() + "', "
                       + " WHERE " + KEY_SECTION_ID + "=" + c.getId();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public void deleteSection(int id) {
        String query = "DELETE * FROM " + TABLE_SECTION + " WHERE "
                       + KEY_SECTION_ID + "=" + id;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public ArrayList<Section> getSections() {
        String query = "select * from "
                       + TABLE_SECTION + " order by " + KEY_SECTION_ID + " desc";

        ArrayList<Section> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(KEY_SECTION_ID);
                String title = rs.getString(KEY_SECTION_TITLE);
                Section section = new Section(id, title);
                result.add(section);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> getSectionsInString() {
        String query = "select * from "
                       + TABLE_SECTION + " order by " + KEY_SECTION_ID;

        ArrayList<String> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String title = rs.getString(KEY_SECTION_TITLE);
                result.add(title);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }

    public int getSectionByTitle(String title) {

        String query = "select " + KEY_SECTION_ID + " from "
                       + TABLE_SECTION + " where " + KEY_SECTION_TITLE + " = '" + title + "'";

        int id = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                id = rs.getInt(KEY_SECTION_ID);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }

        return id;
    }

    //</editor-fold>
    // <editor-fold desc="Notification">
    public void addNotify(Notify n) {
        String query = "INSERT INTO " + TABLE_NOTIFY + "(" + KEY_NOTIFY_PERSON_ID
                       + ", " + KEY_NOTIFY_TEXT + "," + KEY_NOTIFY_CREATED_DATE
                       + ") VALUES (" + n.getPersonid() + " , '"
                       + n.getText() + " ', "
                       + DataUtils.getCurentDateInLong() + ")";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            // query = "SELECT MAX(id) FROM users";
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public Notify getNotify(int notifyId) {

        String query = "select * from "
                       + TABLE_NOTIFY + " where" + " " + KEY_NOTIFY_ID + "=" + notifyId;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                int id = rs.getInt(KEY_NOTIFY_ID);
                String text = rs.getString(KEY_NOTIFY_TEXT);
                int personId = rs.getInt(KEY_NOTIFY_PERSON_ID);
                Long createdDate = rs.getLong(KEY_NOTIFY_CREATED_DATE);

                Notify notify = new Notify(id, personId, text, createdDate);
                return notify;
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }

        return null;
    }

    public void updateNotify(Notify notify) {
        String query = "UPDATE " + TABLE_NOTIFY + " SET "
                       + KEY_NOTIFY_PERSON_ID + "=' " + notify.getPersonid() + "', "
                       + KEY_NOTIFY_TEXT + "='" + notify.getText() + "', "
                       + KEY_NOTIFY_CREATED_DATE + " = " + notify.getCreatedDate()
                       + " WHERE " + KEY_NOTIFY_ID + "=" + notify.getId();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public void deleteNotify(int id) {
        String query = "DELETE * FROM " + TABLE_NOTIFY + " WHERE "
                       + KEY_NOTIFY_ID + "=" + id;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public ArrayList<Notify> getNotices() {
        String query = "select * from "
                       + TABLE_NOTIFY + " order by " + KEY_NOTIFY_ID
                       + " desc";

        ArrayList<Notify> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(KEY_NOTIFY_ID);
                String text = rs.getString(KEY_NOTIFY_TEXT);
                int personId = rs.getInt(KEY_NOTIFY_PERSON_ID);
                Long createdDate = rs.getLong(KEY_NOTIFY_CREATED_DATE);
                Notify notify = new Notify(id, personId, text, createdDate);
                //loadExecutorServices(executor);
                result.add(notify);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Bookmark">
    public void addBookmark(Bookmarks bookmr) {
        String query = "INSERT INTO " + TABLE_BOOKMARKS + "(" + KEY_BOOKMARK_PERSON_ID
                       + ", " + KEY_BOOKMARK_EXECUTOR_ID
                       + ") VALUES (" + bookmr.getPersonId() + " , "
                       + bookmr.getExecutorId() + ")";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public Bookmarks getBookmark(int bookmarkId) {

        String query = "select * from "
                       + TABLE_BOOKMARKS + " where" + " " + KEY_BOOKMARK_PART_ID + "=" + bookmarkId;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                int id = rs.getInt(KEY_BOOKMARK_PART_ID);
                int personId = rs.getInt(KEY_BOOKMARK_PERSON_ID);
                int executorId = rs.getInt(KEY_BOOKMARK_EXECUTOR_ID);

                Bookmarks bookmark = new Bookmarks(id, personId, executorId);
                return bookmark;
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }

        return null;
    }

    public void updateBookmark(Bookmarks bookmarks) {
        String query = "UPDATE " + TABLE_BOOKMARKS + " SET "
                       + KEY_BOOKMARK_PERSON_ID + "=" + bookmarks.getPersonId() + ", "
                       + KEY_BOOKMARK_EXECUTOR_ID + "= " + bookmarks.getExecutorId()
                       + " WHERE " + KEY_BOOKMARK_PART_ID + " = " + bookmarks.getId();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public void deleteBookm(int id) {
        String query = "DELETE * FROM " + TABLE_BOOKMARKS + " WHERE "
                       + KEY_BOOKMARK_PART_ID + "=" + id;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public ArrayList<Bookmarks> getBookmarks() {
        String query = "select * from "
                       + TABLE_BOOKMARKS + " order by " + KEY_BOOKMARK_PART_ID
                       + " desc";

        ArrayList<Bookmarks> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(KEY_BOOKMARK_PART_ID);
                int personId = rs.getInt(KEY_BOOKMARK_PERSON_ID);
                int executorId = rs.getInt(KEY_BOOKMARK_EXECUTOR_ID);
                Bookmarks bookm = new Bookmarks(id, personId, executorId);
                result.add(bookm);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }
    // </editor-fold>

    //<editor-fold desc="EXECUTNPERSON">
    public int getExecutorIdByPersonId(int personId) {
        Person p = this.getPerson(personId);
        if (p == null) {
            return -1;
        }

        String query = "SELECT " + KEY_EXECUTORNPERSON_EXECUTOR_ID
                       + " FROM " + TABLE_EXECUTORNPERSON + " WHERE " + KEY_EXECUTORNPERSON_PERSON_ID + "= " + personId;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.first()) {
                int executorId = rs.getInt(KEY_EXECUTORNPERSON_EXECUTOR_ID);
                Executor executor = getExecutor(executorId);
                if (executor == null) {
                    return -1;
                }
                else {
                    return executorId;
                }
            }
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }

        return -1;
    }

    //</editor-fold>
    //<editor-fold desc="Responses">
    public void addResponse(Response response) {
        String query = "INSERT INTO " + TABLE_RESPONSE + "(" + KEY_RESPONSE_ORDER_ID + ", " + KEY_RESPONSE_PERSON_ID
                       + ", " + KEY_RESPONSE_PRICE + ", " + KEY_RESPONSE_TEXT + ") VALUES (" + response.getOrderId() + ",  "
                       + response.getPersonId() + ", " + response.getPrice()
                       + ",  '" + response.getText() + "')";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public Response getResponse(int id) {

        String query = "select * from " + TABLE_RESPONSE
                       + " where " + KEY_RESPONSE_ID + "=" + id;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                Response s = this.getResponseFromRs(rs);
                return s;
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }

        return null;
    }

    public void updateResponse(Response r) {
        String query = "UPDATE " + TABLE_RESPONSE + " SET "
                       + KEY_RESPONSE_ORDER_ID + "=" + r.getOrderId() + ", "
                       + KEY_RESPONSE_PERSON_ID + "=" + r.getPersonId() + ", "
                       + KEY_RESPONSE_PRICE + "=" + r.getPrice() + ", "
                       + KEY_RESPONSE_TEXT + "= " + r.getText() + ", "
                       + " WHERE " + KEY_RESPONSE_ID + "=" + r.getId();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);

            query = "SELECT MAX(id) FROM users";

            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public void deleteResponse(int id) {
        String query = "DELETE * FROM " + TABLE_RESPONSE + " WHERE "
                       + KEY_RESPONSE_ID + "=" + id;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);

            stmt = con.createStatement();
            stmt.execute(query);
            con.close();
            stmt.close();
        } catch (Exception ex) {
        }
    }

    public ArrayList<Response> getResponses() {
        String query = "select * from "
                       + TABLE_RESPONSE + " order by " + KEY_RESPONSE_ID
                       + " desc";

        ArrayList<Response> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, DBUSER, DBPASSWORD);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Response r = this.getResponseFromRs(rs);
                result.add(r);
            }
            con.close();
            stmt.close();
            rs.close();

        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return result;
    }

    private Response getResponseFromRs(ResultSet rs) throws Exception {
        int id = rs.getInt(KEY_RESPONSE_ID);
        int orderId = rs.getInt(KEY_RESPONSE_ORDER_ID);
        int personId = rs.getInt(KEY_RESPONSE_PERSON_ID);
        double price = rs.getDouble(KEY_RESPONSE_PRICE);
        String text = rs.getString(KEY_RESPONSE_TEXT);
        Response r = new Response(id, orderId, personId, price, text);
        return r;
    }
    //</editor-fold>
}
