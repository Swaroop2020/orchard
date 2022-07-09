$(document).ready(
		function() {

			// SUBMIT FORM
			$("#companyForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					name : $("#companyName").val(),
					irda : $("#irda").val(),
					contact : $("#contact").val(),
					
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "addCompany",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$("#postResultDiv").html(
									""      + " Company added succesfully <br>"
											+ "---> Congrats !!" + "</p>");
						} else {
							$("#postResultDiv").html("<strong>Error</strong>");
						}
						console.log(result);
					},
					error : function(e) {
						alert("Error!")
						console.log("ERROR: ", e);
					}
				});
			}

		})