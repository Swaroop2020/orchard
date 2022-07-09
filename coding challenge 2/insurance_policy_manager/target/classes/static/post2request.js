$(document).ready(
		function() {

			// SUBMIT FORM
			$("#policyForm").submit(function(event) {
				// Prevent the form from submitting via the browser.
				event.preventDefault();
				ajaxPost();
			});

			function ajaxPost() {

				// PREPARE FORM DATA
				var formData = {
					name : $("#policyName").val(),
					premium : $("#premium").val(),
					period : $("#period").val(),
					companies:[$("#companies").val()],
					
				}

				// DO POST
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "addPolicies",
					data : JSON.stringify(formData),
					dataType : 'json',
					success : function(result) {
						if (result.status == "success") {
							$("#postResultDiv").html(
									""      + " Policy added succesfully <br>"
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