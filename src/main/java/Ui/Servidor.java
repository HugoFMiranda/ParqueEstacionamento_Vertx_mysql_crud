package Ui;

/**
 * @author Hugo
 */
import Model.*;
import Controller.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
//import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.*;

import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import io.vertx.ext.web.Session;
import java.lang.reflect.RecordComponent;
import java.sql.SQLException;
import java.util.List;

import static io.vertx.ext.web.handler.StaticHandler.DEFAULT_WEB_ROOT;

public class Servidor extends AbstractVerticle {

    // The default web root.
    private String webRoot = DEFAULT_WEB_ROOT;
    private int porta = 8111;
    private login authProvider;
    boolean isAuthenticated = false;
    Object user;

    @Override
    // A method that is called when the verticle is deployed.
    public void start(Promise<Void> promise) throws Exception {
        Router router = Router.router(vertx);
        // por pré-definição serve index.html
        router.route("/*").handler(StaticHandler.create(webRoot));

        // pedido de recurso estático (página nova)
        router.route().handler(BodyHandler.create());
        router.route(HttpMethod.POST, "/submitclientes").handler(this::addCliente);
        router.route(HttpMethod.POST, "/submitgestores").handler(this::addGestor);

        // * login
        // A route handler that is called when the client sends a POST request to the
        // /submitlogin
        // path.
        router.route(HttpMethod.POST, "/submitlogin").handler(this::verflogin);

        // Creating a session.
        // SessionStore store = LocalSessionStore.create(vertx);
        // router.route().handler(SessionHandler.create(store));

        // router.get("/login").handler(rs -> {
        // rs.response().sendFile("/login.html");
        // if (isAuthenticated) {
        // rs.response().end();
        // }
        // });

        // * cliente
        // Checking if the user is authenticated and if not, it redirects the user to
        // the login page.
        router.get("/Cliente/*").handler(rs -> {
            if (!isAuthenticated) {
                rs.response().sendFile("../login.html");
                rs.response().end();
            } else {
                rs.next();
            }
        });

        // * Gestor
        // Checking if the user is authenticated and if not, it redirects the user to
        // the login page.
        router.get("/Gestor/*").handler(rs -> {
            if (!isAuthenticated) {
                rs.response().sendFile("../login.html");
                rs.response().end();
            } else {
                rs.next();
            }
        });

        // * getUsr
        // A route handler that is called when the client sends a GET request to the
        // /getUsr path.
        router.get("/getUsr").handler(this::getUsr);

        // * sairUsr
        // A route handler that is called when the client sends a GET request to the
        // /sairUsr path.
        router.get("/sairUsr").handler(this::sairUsr);

        // * Viaturas
        router.post("/Cliente/addviatura").handler(this::addviatura);
        router.get("/Cliente/getViaturas").handler(this::getViaturas);
        router.put("/Cliente/updateviatura/:id").handler(this::updateViatura);
        router.delete("/Cliente/deleteviatura/:id").handler(this::deleteViatura);

        router.post("/Gestor/addviatura").handler(this::addviatura);
        router.get("/Gestor/getViaturas").handler(this::getViaturas);
        router.put("/Gestor/updateviatura/:id").handler(this::updateViatura);
        router.delete("/Gestor/deleteviatura/:id").handler(this::deleteViatura);

        // * TODO Faturas
        router.post("/Gestor/addFatura").handler(this::addFatura);
        router.get("/Gestor/getFaturas").handler(this::getFaturas);
        router.put("/Gestor/updateFatura/:id").handler(this::updateFaturas);
        router.delete("/Gestor/deleteFatura/:id").handler(this::deleteFatura);

        // *cria servidor HTTP
        HttpServerOptions options = new HttpServerOptions();
        options.setPort(porta);
        // It creates a HTTP server and listens on the port specified in the options.
        vertx.createHttpServer(options)
                .requestHandler(router)
                .listen(res -> {
                    if (res.succeeded()) {
                        System.out.println("Porta: " + porta);
                        promise.complete();
                    } else {
                        promise.fail(res.cause());
                    }
                });
    }

    @Override
    // A method that is called when the verticle is undeployed.
    public void stop() {
        System.out.println("--->Stopped! ");
    }

    /**
     * The main function is the entry point of the program
     */
    public static void main(String[] args) {
        // FileSystemOptions fsOptions = new FileSystemOptions();
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Servidor());
    }

    /**
     * It checks if the user is authenticated and if so, it creates a session
     * 
     * @param rc RoutingContext
     */
    private void verflogin(RoutingContext rc) {

        String email = rc.request().getParam("emailL");
        String password = rc.request().getParam("passwordL");
        LoginController login = new LoginController();
        try {
            authProvider = login.createLogin(email, password);
            int n = login.verfLogin(email, password);
            HttpServerResponse response = rc.response();
            if (n != 0) {
                response.putHeader("content-type", "application/json; charset=utf-8");
                if (n == 1) {
                    isAuthenticated = true;
                    response.setStatusCode(200); // 200 se cliente
                    user = login.getCliente();
                }
                if (n == 2) {
                    isAuthenticated = true;
                    response.setStatusCode(201); // 201 se gestor
                    user = login.getGestor();
                }
                response.end();
            } else {
                isAuthenticated = false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * The addCliente function adds a new client to the database.
     * 
     * @param rc Pass the request context to the function
     *
     */
    private void addCliente(RoutingContext rc) {

        /*
         ** NOTE client parameters
         * private int id, nif;
         * private String nome, email, password;
         */
        String name = rc.request().getParam("nomeR");
        String email = rc.request().getParam("emailR");
        String password = rc.request().getParam("passwordR2");
        int nif = Integer.valueOf(rc.request().getParam("nifR"));
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(200); // ok e recurso criado
            ClienteController cc = new ClienteController();
            response.end(Json.encodePrettily(cc.registerCliente(name, email, nif,
                    password)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * It receives a request from the client, gets the parameters from the
     * request,
     * creates a new
     * GestorController object, calls the registerGestor method from the
     * GestorController object, and
     * sends the response to the client
     *
     * @param rc RoutingContext
     */
    private void addGestor(RoutingContext rc) {

        /*
         ** NOTE gestor parameters
         * private int id, nif;
         * private String nome, email, password;
         */
        String name = rc.request().getParam("nomeR");
        String email = rc.request().getParam("emailR");
        String password = rc.request().getParam("passwordR2");
        int nif = Integer.valueOf(rc.request().getParam("nifR"));
        try {
            HttpServerResponse response = rc.response();
            response.putHeader("content-type", "application/json; charset=utf-8");
            response.setStatusCode(201); // ok e recurso criado
            GestorController cc = new GestorController();
            response.end(Json.encodePrettily(cc.registerGestor(name, email, nif,
                    password)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * The function takes a RoutingContext object as a parameter, and then sets the
     * response header to
     * "application/json; charset=utf-8" and then ends the response with the user
     * object encoded as a
     * JSON string
     * 
     * @param rc RoutingContext
     */
    private void getUsr(RoutingContext rc) {
        HttpServerResponse response = rc.response();
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.end(Json.encodePrettily(user));
    }

    /**
     * The sairUsr function is used to log out the user.
     * 
     *
     * @param rc Access the request and response objects
     *
     *
     */
    private void sairUsr(RoutingContext rc) {
        HttpServerResponse response = rc.response();
        response.putHeader("content-type", "application/json; charset=utf-8");
        response.setStatusCode(200);
        user = null;
        isAuthenticated = false;
        response.end();
    }

    /**
     * It takes a JSON object from the request body, and then it creates a new
     * Viatura object with the
     * data from the JSON object
     * 
     * @param rc RoutingContext
     */
    private void addviatura(RoutingContext rc) {
        // NOTE viatura params
        // this.ano = ano;
        // this.idp = idp;
        // this.tipo = tipo;
        // this.matricula = matricula;
        // this.marca = marca;
        // this.modelo = modelo;
        JsonObject j = rc.getBodyAsJson();
        int idp;
        if ((Cliente) user != null) {
            idp = ((Cliente) this.user).getId();
        } else if ((Gestor) user != null) {
            idp = ((Gestor) this.user).getId();
        } else {
            return;
        }
        try {
            rc.response().setStatusCode(201);
            rc.response().putHeader("content-type", "application/json; charset=utf-8");
            ViaturaController vc = new ViaturaController();
            rc.response().end(Json.encodePrettily(vc.registerViatura(j.getInteger("ano"), idp, j.getString("tipo"),
                    j.getString("matricula"), j.getString("marca"), j.getString("modelo"))));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * It gets the id of the viatura from the request, gets the body of the request
     * as a JsonObject,
     * checks if the id or the JsonObject are null, if they are, it sets the status
     * code to 400 and
     * ends the request, if they aren't, it gets the viatura from the database,
     * checks if the viatura
     * is null, if it is, it sets the status code to 404 and ends the request, if it
     * isn't, it updates
     * the viatura with the data from the JsonObject and returns the updated viatura
     * 
     * @param routingContext The routing context of the request.
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws NumberFormatException
     */
    private void updateViatura(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("id");
        JsonObject json = routingContext.getBodyAsJson();
        if (id == null || json == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            try {
                ViaturaController vc = new ViaturaController();
                Viatura v = vc.getViatura(Integer.parseInt(id));
                if (v == null) {
                    routingContext.response().setStatusCode(404).end();
                } else {
                    v.setTipo(json.getString("nome"));
                    v.setMatricula(json.getString("matricula"));
                    v.setMarca(json.getString("marca"));
                    v.setModelo(json.getString("modelo"));
                    v.setAno(json.getInteger("ano"));
                    v.setIdp(json.getInteger("idp"));
                    routingContext.response()
                            .putHeader("content-type", "application/json; charset=utf-8")
                            .end(Json.encodePrettily(v));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * It receives a request from the client, gets the id of the object to be
     * deleted, creates a new
     * instance of the controller class, and calls the deleteViatura method
     * 
     * @param routingContext The routing context of the request.
     */
    private void deleteViatura(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("id");
        JsonObject json = routingContext.getBodyAsJson();
        if (id == null || json == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            ViaturaController vc = new ViaturaController();
            try {
                vc.deleteViatura(Integer.parseInt(id));
                routingContext.response().setStatusCode(204).end();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getViaturas(RoutingContext routingContext) {
        ViaturaController vc = new ViaturaController();
        int idp;
        if ((Cliente) user != null) {
            idp = ((Cliente) this.user).getId();
        } else if ((Gestor) user != null) {
            idp = ((Gestor) this.user).getId();
        } else {
            return;
        }
        try {
            List<Viatura> listV = vc.getAll(idp);
            if (!listV.isEmpty()) {
                routingContext.response()
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(listV));
            } else {
                routingContext.response().end();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SECTION fatura
    private void addFatura(RoutingContext rc) {
        // NOTE viatura params
        // this.ano = ano;
        // this.idp = idp;
        // this.tipo = tipo;
        // this.matricula = matricula;
        // this.marca = marca;
        // this.modelo = modelo;
        JsonObject j = rc.getBodyAsJson();
        int idp;
        if ((Cliente) user != null) {
            idp = ((Cliente) this.user).getId();
        } else if ((Gestor) user != null) {
            idp = ((Gestor) this.user).getId();
        } else {
            return;
        }
        try {
            rc.response().setStatusCode(201);
            rc.response().putHeader("content-type", "application/json; charset=utf-8");
            ViaturaController vc = new ViaturaController();
            rc.response().end(Json.encodePrettily(vc.registerViatura(j.getInteger("ano"), idp, j.getString("tipo"),
                    j.getString("matricula"), j.getString("marca"), j.getString("modelo"))));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void updateFaturas(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("id");
        JsonObject json = routingContext.getBodyAsJson();
        if (id == null || json == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            try {
                ViaturaController vc = new ViaturaController();
                Viatura v = vc.getViatura(Integer.parseInt(id));
                if (v == null) {
                    routingContext.response().setStatusCode(404).end();
                } else {
                    v.setTipo(json.getString("nome"));
                    v.setMatricula(json.getString("matricula"));
                    v.setMarca(json.getString("marca"));
                    v.setModelo(json.getString("modelo"));
                    v.setAno(json.getInteger("ano"));
                    v.setIdp(json.getInteger("idp"));
                    routingContext.response()
                            .putHeader("content-type", "application/json; charset=utf-8")
                            .end(Json.encodePrettily(v));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void deleteFatura(RoutingContext routingContext) {
        final String id = routingContext.request().getParam("id");
        JsonObject json = routingContext.getBodyAsJson();
        if (id == null || json == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            ViaturaController vc = new ViaturaController();
            try {
                vc.deleteViatura(Integer.parseInt(id));
                routingContext.response().setStatusCode(204).end();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getFaturas(RoutingContext routingContext) {
        ViaturaController vc = new ViaturaController();
        int idp;
        if ((Cliente) user != null) {
            idp = ((Cliente) this.user).getId();
        } else if ((Gestor) user != null) {
            idp = ((Gestor) this.user).getId();
        } else {
            return;
        }
        try {
            List<Viatura> listV = vc.getAll(idp);
            if (!listV.isEmpty()) {
                routingContext.response()
                        .putHeader("content-type", "application/json; charset=utf-8")
                        .end(Json.encodePrettily(listV));
            } else {
                routingContext.response().end();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
