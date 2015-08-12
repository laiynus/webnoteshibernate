package by.khrapovitsky.controller;

import by.khrapovitsky.dao.*;
import by.khrapovitsky.model.Note;
import by.khrapovitsky.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;


public class NotesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
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
            if (flag.contains("alltable")) {
                allNotesRequest(request, response);
            }
            if (flag.contains("registration")) {
                registrationRequest(request, response);
            }
        }
        String tmp = request.getParameter("submit");
        if (tmp != null) {
            if (tmp.contains("Add")) {
                addRequest(request, response);
            }
            if (tmp.contains("Edit")) {
                editRequest(request, response);
            }
        }
    }

    protected void logInRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        Boolean flag = false;
        User tmpUser = Factory.getInstance().getUsersDAO().getUser(request.getParameter("login"));
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
            Note note = Factory.getInstance().getNotesDAO().getNoteWithUser(Integer.parseInt(request.getParameter("id")));
            if (note != null && note.getUser().getLogin().equals(request.getSession().getAttribute("acptLogin"))) {
                Factory.getInstance().getNotesDAO().delete(note);
                response.sendRedirect("index.jsp");
            } else {
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
            User user = new User((String) request.getSession().getAttribute("acptLogin"), null);
            Note note = new Note(user, request.getParameter("note"), new Timestamp(new java.util.Date().getTime()));
            Factory.getInstance().getNotesDAO().insert(note);
            response.sendRedirect("index.jsp");
        }
    }

    protected void editRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("notlogin.jsp");
        } else {
            Note note = Factory.getInstance().getNotesDAO().getNoteWithUser(Integer.parseInt(request.getParameter("idEdit")));
            if (note != null && note.getUser().getLogin().equals(request.getSession().getAttribute("acptLogin"))) {
                note.setNote(request.getParameter("note"));
                note.setDateTimeCreate(new Timestamp(new java.util.Date().getTime()));
                Factory.getInstance().getNotesDAO().update(note);
                response.sendRedirect("index.jsp");
            } else {
                response.sendRedirect("403.jsp");
            }
        }
    }

    protected void lastNotesRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("notlogin.jsp");
        } else {
            User user = new User((String) request.getSession().getAttribute("acptLogin"), null);
            List<Note> listNote = Factory.getInstance().getNotesDAO().getLastUserNotes(user);
            request.setAttribute("listNote", listNote);
        }
    }

    protected void allNotesRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        if (request.getSession().getAttribute("acptLogin") == null) {
            response.sendRedirect("notlogin.jsp");
        } else {
            User user = new User((String) request.getSession().getAttribute("acptLogin"), null);
            List<Note> listNote = Factory.getInstance().getNotesDAO().getUserNotes(user);
            request.setAttribute("listNote", listNote);
        }
    }

    protected void registrationRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        Boolean flag = false;
        User tmpUser = Factory.getInstance().getUsersDAO().getUser(request.getParameter("login"));
        if (tmpUser != null) {
            flag = true;
            response.sendRedirect("registration.jsp?message=" + URLEncoder.encode("This user is already exist", "UTF-8"));
        } else {
            if (request.getParameter("password").equals(request.getParameter("reenterpassword"))) {
                tmpUser = new User(request.getParameter("login"), request.getParameter("password"));
                Factory.getInstance().getUsersDAO().insert(tmpUser);
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
