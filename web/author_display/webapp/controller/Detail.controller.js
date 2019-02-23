sap.ui.define([
	"sap/ui/model/json/JSONModel",
	"sap/ui/core/mvc/Controller"
], function (JSONModel, Controller) {
	"use strict";

	return Controller.extend("author_display.controller.Detail", {

		onInit: function () {
			this.oRouter = this.getOwnerComponent().getRouter();
			this.oModel = this.getOwnerComponent().getModel();

			this.oRouter.getRoute("master").attachPatternMatched(this._onProductMatched, this);
			this.oRouter.getRoute("detail").attachPatternMatched(this._onProductMatched, this);
			this.oRouter.getRoute("detailDetail").attachPatternMatched(this._onProductMatched, this);
		},

		handleItemPress: function (oEvent) {
			var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(2),
				bookPath = oEvent.getSource().getBindingContext("authors").getPath(),
				book = bookPath.split("/").slice(-1).pop();

			this.oRouter.navTo("detailDetail", {layout: oNextUIState.layout, book: book});
		},

		handleFullScreen: function () {
			var sNextLayout = this.oModel.getProperty("/actionButtonsInfo/midColumn/fullScreen");
			this.oRouter.navTo("detail", {layout: sNextLayout, author: this._author});
		},

		handleExitFullScreen: function () {
			var sNextLayout = this.oModel.getProperty("/actionButtonsInfo/midColumn/exitFullScreen");
			this.oRouter.navTo("detail", {layout: sNextLayout, author: this._author});
		},

		handleClose: function () {
			var sNextLayout = this.oModel.getProperty("/actionButtonsInfo/midColumn/closeColumn");
			this.oRouter.navTo("master", {layout: sNextLayout});
		},

		_onProductMatched: function (oEvent) {
			this._author = oEvent.getParameter("arguments").author || this._author || "0";
			this.getView().bindElement({
				path: "/" + this._author,
				model: "authors",
				parameters: {
					expand: "toBooks,toAddress"
				},
			});
		},

		onDelete : function () {
            // var oSelected = this.byId("authorsTable").getSelectedItem();
            //
            // if (oSelected) {
            //     oSelected.getBindingContext().delete("$auto").then(function () {
            //         MessageToast.show(this._getText("deletionSuccessMessage"));
            //     }.bind(this), function (oError) {
            //         MessageBox.error(oError.message);
            //     });
            // }
        },

		onEdit : function () {
            var editBtn = this.getView().byId("editBtn");
            editBtn.setEnabled(false);

            var authorNameText = this.getView().byId("authorNameText");
            authorNameText.setEnabled(true);
            authorNameText.focus();
        },

        onAuthorNameSubmit : function () {

            function successHandler(){
                console.log("updated successfully");
            }

            function errorHandler(){
                console.log("updating ERROR!");
            }

            //disable textField
            var authorNameText = this.getView().byId("authorNameText");
            authorNameText.setEnabled(false);

            var oModel = this.getView().getModel("authors");
            oModel.sDefaultUpdateMethod = "PUT";
            var sName = authorNameText.getValue();
            var sPath = this.getView().getElementBinding('authors').sPath;
            // var sFullPath = oModel

            var oData = {
                name: sName,
                created: new Date(),
                updated: new Date()
            };

            // $.ajax({
            //     type: "PUT",
            //     url: 'proxy/http/services.odata.org/V3/(S(javsoqela2hqsduwyhasapr2))/OData/OData.svc/Products(' + oProductId + ')',
            //     dataType: "json",
            //     data: JSON.stringify(oData),
            //     contentType: "application/json; charset=utf-8" ,
            //     success: successHandler,
            //     error: errorHandler
            // });

            oModel.update(sPath, oData, {
                success: successHandler,
                error: errorHandler
            });

            var editBtn = this.getView().byId("editBtn");
            editBtn.setEnabled(true);
        }
	});
}, true);
