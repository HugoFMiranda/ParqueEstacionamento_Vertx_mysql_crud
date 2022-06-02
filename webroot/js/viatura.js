/**
 * The function initialData() calls the function load() and then calls the function initModal().
 */
function initialData() {
    load();
    initModal();
}

/**
 * It takes the values from the form and sends them to the server.
 * @param marca - $("#marca").val(),
 * @param modelo - modelo,
 * @param tipo - tipo,
 * @param ano - $("#ano").val(),
 */
function create(marca, modelo, tipo, ano) {
    $.post("/Cliente/addviatura", JSON.stringify({ marca: marca, modelo: modelo, tipo: tipo, ano: ano }), function() {
        load();
    }, "json");
}

/**
 * It's a function that takes an id as a parameter and then uses ajax to call the delete method in the
 * controller.
 * @param id - the id of the client to be deleted
 */
function remove(id) {
    $.ajax({
        method: "DELETE",
        url: "/Cliente/deleteviatura/" + id
    }).done(function() {
        load();
    });
}

/**
 * It takes the id, name and origin of a car and sends it to the server to be updated.
 * @param id - the id of the car
 * @param name - name,
 * @param origin - "",
 */
function update(id, marca, modelo, tipo, ano) {
    $.ajax({
        method: "PUT",
        url: "/Cliente/updateviatura/" + id,
        data: JSON.stringify({ marca: marca, modelo: modelo, tipo: tipo, ano: ano })
    }).done(function() {
        load();
    });
}

/**
 * It loads the data from the database and puts it in a table.
 */
function load() {
    $("#content").children().remove();
    $.getJSON("/Cliente/getViaturas", function(data) {
        $.each(data, function(key, val) {
            $("<tr><td>" + val.id + "</td><td>" + val.matricula + "</td><td>" + val.marca + "</td><td>" + val.modelo + "</td><td>" + val.tipo + "</td><td>" + val.ano + "</td>" +
                "<td>" +
                "<button data-action='edit' class='btn btn-primary btn-sm product-edit' " +
                "data-toggle='modal' " +
                "data-target='#productModal' " +
                "data-tipo='" + val.tipo + "' " +
                "data-marca='" + val.matricula + "' " +
                "data-modelo='" + val.modelo + "' " +
                "data-marca='" + val.marca + "' " +
                "data-ano='" + val.ano + "'>" +
                "<span class='glyphicon glyphicon-pencil'></span>" +
                "</button>" +
                "&nbsp;" +
                "<button class='btn btn-danger btn-sm product-delete' data-id='" + val.id + "'>" +
                "   <span class='glyphicon glyphicon-minus'></span>" +
                "</button>" +
                "</td>" +
                "</tr>").appendTo("#content");
        });
        initCallbacks();
    });
}

/**
 * When the user clicks on the delete button, get the id of the product and call the remove function.
 */
function initCallbacks() {
    $(".product-delete").unbind().click(function() {
        var id = $(this).data("id");
        remove(id);
    });
}

/**
 * When the modal is shown, if the action is add, then set the title to "Add a product", otherwise set
 * the title to "Edit a product" and populate the fields with the data from the button.
 */
function initModal() {
    $("#productModal").on('show.bs.modal', function(event) {
        var button = $(event.relatedTarget);
        var action = button.data('action');
        var id = button.data('id');
        var productAction = $("#productAction");
        productAction.unbind();
        var modal = $(this);
        if (action === "add") {
            modal.find('.modal-title').text("Adicionar uma viatura");
            modal.find('#product-matricula').val("");
            modal.find('#product-marca').val("");
            modal.find('#product-modelo').val("");
            modal.find('#product-tipo').val("");
            modal.find('#product-ano').val("");
            productAction.click(function() {
                create($("#product-matricula").val(), $("#product-marca").val(), $("#product-modelo").val(), $("#product-tipo").val(), $("#product-ano").val());
                $('#productModal').modal('toggle');
            });
        } else if (action === "edit") {
            modal.find('.modal-title').text("Editar uma viatura");
            modal.find('#product-matricula').val(button.data("matricula"));
            modal.find('#product-marca').val(button.data("marca"));
            modal.find('#product-modelo').val(button.data("modelo"));
            modal.find('#product-tipo').val(button.data("tipo"));
            modal.find('#product-ano').val(button.data("ano"));
            productAction.click(function() {
                update(id, $("#product-matricula").val(), $("#product-marca").val(), $("#product-modelo").val(), $("#product-tipo").val(), $("#product-ano").val());
                $('#productModal').modal('toggle');
            });
        }
    });
}