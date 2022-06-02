/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function initialData() {
    load();
    initModal();
}

function create(name, origin) {
    $.post("/add", JSON.stringify({name: name, origin: origin}), function () {
        load();
    }, "json");
}

function remove(id) {
    $.ajax({
        method: "DELETE",
        url: "/delete/" + id
    }).done(function () {
        load();
    });
}

function update(id, name, origin) {
    $.ajax({
        method: "PUT",
        url: "/update/" + id,
        data: JSON.stringify({name: name, origin: origin})
    }).done(function () {
        load();
    });
}

function load() {
    $("#content").children().remove();
    $.getJSON("/all", function (data) {
        $.each(data, function (key, val) {
            $("<tr><td>" + val.id + "</td><td>" + val.name + "</td><td>" + val.origin + "</td>" +
                    "<td>" +
                    "<button data-action='edit' class='btn btn-primary btn-sm product-edit' " +
                    "data-toggle='modal' " +
                    "data-target='#productModal' " +
                    "data-name='" + val.name + "' " +
                    "data-origin='" + val.origin + "' " +
                    "data-id='" + val.id + "'>" +
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

function initCallbacks() {
    $(".product-delete").unbind().click(function () {
        var id = $(this).data("id");
        remove(id);
    });
}

function initModal() {
    $("#productModal").on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var action = button.data('action');
        var id = button.data('id');
        var productAction = $("#productAction");
        productAction.unbind();
        var modal = $(this);
        if (action === "add") {
            modal.find('.modal-title').text("Add a bottle");
            modal.find('#product-name').val("");
            modal.find('#product-origin').val("");
            productAction.click(function () {
                create($("#product-name").val(), $("#product-origin").val());
                $('#productModal').modal('toggle');
            });
        } else {
            modal.find('.modal-title').text("Edit a bottle");
            modal.find('#product-name').val(button.data("name"));
            modal.find('#product-origin').val(button.data("origin"));
            productAction.click(function () {
                update(id, $("#product-name").val(), $("#product-origin").val());
                $('#productModal').modal('toggle');
            });
        }
    });
}

