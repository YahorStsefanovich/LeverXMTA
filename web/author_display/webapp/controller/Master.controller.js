sap.ui.define([
	"sap/ui/model/json/JSONModel",
	"sap/ui/core/mvc/Controller",
	"sap/ui/model/Filter",
	"sap/ui/model/FilterOperator",
	'sap/ui/model/Sorter'
], function (JSONModel, Controller, Filter, FilterOperator, Sorter) {
	"use strict";

	return Controller.extend("author_display.controller.Master", {

	    onInit: function () {
			this.oRouter = this.getOwnerComponent().getRouter();
			this._bDescendingSort = false;
        },

        //works
		onListItemPress: function (oEvent) {
			var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1),
				authorPath = oEvent.getSource().getBindingContext("authors").getPath(),
				author = authorPath.split("/").slice(-1).pop();
			this.oRouter.navTo("detail", {layout: oNextUIState.layout, author: author});
		},
		
		//works
		onSearch: function (oEvent) {
			var oTableSearchState = [],
				sQuery = oEvent.getParameter("query");

			if (sQuery && sQuery.length > 0) {
				oTableSearchState = [new Filter("name", FilterOperator.Contains, sQuery)];
			}

			this.getView().byId("authorsTable").getBinding("items").filter(oTableSearchState, "Application");
		},


		onAdd: function (oEvent) {
			// MessageBox.show("This functionality is not ready yet.", {
			// 	icon: MessageBox.Icon.INFORMATION,
			// 	title: "Aw, Snap!",
			// 	actions: [MessageBox.Action.OK]
			// });
		},

		//works
		onSort: function (oEvent) {
			this._bDescendingSort = !this._bDescendingSort;
			var oView = this.getView(),
				oTable = oView.byId("authorsTable"),
				oBinding = oTable.getBinding("items"),
				oSorter = new Sorter("name", this._bDescendingSort);

			oBinding.sort(oSorter);
		},

        //works
        onUpdateFinished: function (oEvent) {
            // update the worklist's object counter after the table update
            var sTitle,
                oTable = oEvent.getSource(),
                iTotalItems = oEvent.getParameter("total");
            // only update the counter if the length is final and
            // the table is not empty
            if (iTotalItems && oTable.getBinding("items").isLengthFinal()) {
                sTitle = 'Authors(' + iTotalItems + ')';
            } else {
                sTitle = "Authors";
            }
            this.getView().byId("authorsTableTitle").setText(sTitle);
        },

        onCreate : function () {
            // var oList = this.byId("authorsTable"),
            //     oBinding = oList.getBinding("items"),
            //     oContext = oBinding.create({
            //         "UserName" : "",
            //         "FirstName" : "",
            //         "LastName" : "",
            //         "Age" : "18"
            //     });
            //
            // oContext.created().then(function () {
            //     oBinding.refresh();
            // });
            //
            // this._setUIChanges();
            // this.getView().getModel("appView").setProperty("/usernameEmpty", true);
            //
            // oList.getItems().some(function (oItem) {
            //     if (oItem.getBindingContext() === oContext) {
            //         oItem.focus();
            //         oItem.setSelected(true);
            //         return true;
            //     }
            // });
        }
	});
}, true);
