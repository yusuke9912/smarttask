/**
 * 
 */

(function() {
	'use strict'

	const selectElement = document.getElementById('max_count_select');

	if (selectElement != null) {
		// select要素のchangeイベントリスナーを設定
		selectElement.addEventListener('change', () => {
			// フォームを送信
			document.getElementById('max_count_form').submit();
		});
	}

	// 完了チェック
	const completeCheckboxElements = Array.from(document.getElementsByClassName('complete-checkbox'));
	const completeFormElements = document.getElementsByClassName('complete-form');

	completeCheckboxElements.forEach((completeCheckboxElement, index) => {
		completeCheckboxElement.addEventListener('change', () => {
			completeCheckboxElement.checked = false;
			completeFormElements[index].submit();

		});
	}
	)


	// Fetch all the forms we want to apply custom Bootstrap validation styles to
	var forms = document.querySelectorAll('.needs-validation')

	// Loop over them and prevent submission
	Array.prototype.slice.call(forms)
		.forEach(function(form) {
			form.addEventListener('submit', function(event) {
				if (!form.checkValidity()) {
					event.preventDefault()
					event.stopPropagation()
				}

				form.classList.add('was-validated')
			}, false)
		})
})()