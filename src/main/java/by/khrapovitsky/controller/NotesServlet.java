package by.khrapovitsky.controller;

import by.khrapovitsky.dao.NotesDAO;
import by.khrapovitsky.dao.NotesDAOImplement;
import by.khrapovitsky.dao.UsersDAO;
import by.khrapovitsky.dao.UsersDAOImplement;
import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


public class NotesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        String flag = request.getParameter("command");
        if (flag != null) {
            if (flag.contains("login")) {
                logInRequest(request, response);
            }
            if (flag.contains("logout")) {
                logOutRequest(request, response);
            }
            if (flag.contains("table")) {
                lastNotesRequest(request, response);
            }
            if (flag.contains("delete")) {
                deleteRequest(request, response);
            }
            if(flag.contains("alltable")){
                allNotesRequest(request, response);
            }
            if(flag.contains("registration")){
                registrationRequest(request, response);
            }
        }
        String tmp = request.getParameter("submit");
        if(tmp != null){
            if(tmp.contains("Add")){
                addRequest(request,response);
            }
            if(tmp.contains("Edit")){
                editRequest(request,response);
            }
        }
    }

    protected void logInRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        UsersDAO usersDAO = new UsersDAOImplement();
        Boolean flag = false;
        User tmpUser;
        tmpUser = usersDAO.getUser(request.getParameter("login"));
        if (tmpUser == null) {
            flag = true;
            response.sendRedirect("login.jsp?message=" + URLEncoder.encode("User not found!", "UTF-8"));
        } else {
            if (tmpUser.getLogin().equals(request.getParameter("login")) && (tmpUser.getPassword().equals(request.getParameter("password")))) {
                request.getSession().setAttribute("acptLogin", tmpUser.getLogin());
            } else {
                flag = true;
                response.sendRedirect("login.jsp?message=" + URLEncoder.encode("Incorrect password!", "UTF-8"));
            }
        }
        if (!flag) {
            response.sendRedirect("index.jsp");
        }

    }

    protected void lastNotesRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("notlogin.jsp");
        } else {
            NotesDAO notesDAO = new NotesDAOImplement();
            List<Note> listNote = notesDAO.getLastUserNotes((String) request.getSession().getAttribute("acptLogin"));
            request.setAttribute("listNote", listNote);

        }
    }

    protected void logOutRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        request.getSession().setAttribute("acptLogin", null);
        response.sendRedirect("login.jsp");
    }

    protected void deleteRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("notlogin.jsp");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            String login = (String) request.getSession().getAttribute("acptLogin");
            NotesDAO notesDAO = new NotesDAOImplement();
            Note note = notesDAO.getNote(id);
            if(note!=null && note.getLogin().equals(login)){
                notesDAO.delete(note);
                response.sendRedirect("index.jsp");
            }else{
                response.sendRedirect("403.jsp");
            }
        }
    }

    protected void addRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("notlogin.jsp");
        } else {
            String noteText = request.getParameter("note");
            NotesDAO notesDAO = new NotesDAOImplement();
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            Note note = new Note((String)request.getSession().getAttribute("acptLogin"),noteText, sqlDate);
            notesDAO.insert(note);
            response.sendRedirect("index.jsp");
        }
    }

    protected void editRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("notlogin.jsp");
        } else {
            String noteText = request.getParameter("note");
            String idtmp = request.getParameter("idEdit");
            int id = Integer.parseInt(idtmp);
            String login = (String) request.getSession().getAttribute("acptLogin");
            NotesDAO notesDAO = new NotesDAOImplement();
            Note note = notesDAO.getNote(id);
            if(note!=null && note.getLogin().equals(login)){
                note.setNote(noteText);
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                note.setDateTimeCreate(sqlDate);
                notesDAO.update(note);
                response.sendRedirect("index.jsp");
            }else{
                response.sendRedirect("403.jsp");
            }



        }
    }

    protected void allNotesRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("notlogin.jsp");
        } else {
            NotesDAO notesDAO = new NotesDAOImplement();
            List<Note> listNote = notesDAO.getUserNotes((String) request.getSession().getAttribute("acptLogin"));
            request.setAttribute("listNote", listNote);

        }
    }

    protected void registrationRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {

        UsersDAO usersDAO = new UsersDAOImplement();
        Boolean flag = false;
        User tmpUser;
        tmpUser = usersDAO.getUser(request.getParameter("login"));
        if (tmpUser != null) {
            flag = true;
            response.sendRedirect("registration.jsp?message=" + URLEncoder.encode("This user is already exist", "UTF-8"));
        } else {
            if (request.getParameter("password").equals(request.getParameter("reenterpassword"))) {
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                tmpUser = new User(login,password);
                usersDAO.insert(tmpUser);
                request.getSession().setAttribute("acptLogin", tmpUser.getLogin());
            } else {
                flag = true;
                response.sendRedirect("registration.jsp?message=" + URLEncoder.encode("Passwords mismatch!", "UTF-8"));
            }
        }
        if (!flag) {
            response.sendRedirect("index.jsp");
        }

    }
}
