/**
 * Search by Keywords
 */

var items;
var url = `http://localhost:5555/`;
fetch(url)
  .then(function (response) {
    return response.json();
  })
  .then(function (data) {
    items = data;
  });

function autocomplete(inp, arr) {

  inp.addEventListener("input", function (e) {
    var a,
      b,
      val = this.value;


    closeAllLists();
    if (val == "") {
      clearKeywords();
    }
    if (!val) {
      return false;
    }

    
    a = document.createElement("DIV");
    a.setAttribute("id", this.id + "autocomplete-list");
    a.setAttribute("class", "autocomplete-items");

    this.parentNode.appendChild(a);

    for (i = 0; i < items.length; i++) {


      if (items[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
        
        b = document.createElement("DIV");

        b.innerHTML =
          "<strong >" + items[i].substr(0, val.length) + "</strong>";
        b.innerHTML += items[i].substr(val.length);

        b.innerHTML += "<input type='hidden' value='" + items[i] + "'>";

        b.addEventListener("click", function (e) {

          inp.value = this.getElementsByTagName("input")[0].value;
          var url = `http://localhost:5555/reviews/search?query=${inp.value}`;

          fetch(url)
            .then(function (response) {
              return response.json();
            })
            .then(function (data) {
              removeKeywords();
              for (let index = 0; index < data.length; index++) {
                var divKeywords = document.createElement("DIV");
                divKeywords.setAttribute("class", "review-item");
                divKeywords.innerHTML = `${data[index].review}`;
                document
                  .getElementById("result-from-keyword")
                  .appendChild(divKeywords);
              }
            });

          closeAllLists();
        });
        a.appendChild(b);
      }
    }
  });

  function clearKeywords(params) {
    var x = [].slice.call(document.getElementsByClassName("review-item"));
    for (var index = 0; index < x.length; index++) {
      x[index].parentNode.removeChild(x[index]);
    }
  }

  function removeKeywords(params) {
    var x = document.getElementsByClassName("review-item");
    for (var i = 0; i < x.length; i++) {
      x[i].parentNode.removeChild(x[i]);
    }
  }
  function closeAllLists(params) {
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      x[i].parentNode.removeChild(x[i]);
    }
  }

  document.addEventListener("click", function (e) {
    closeAllLists(e.target);
  });
}


autocomplete(document.getElementById("searchKeywords"), items);

/**
 *
 * End
 */

function formClick(params) {
  var valueId = document.getElementById("searchId").value;

  let url = `http://localhost:5555/reviews/${valueId}`;
  fetch(url)
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      var divKeywords = document.createElement("DIV");
      divKeywords.setAttribute("class", "review-itemById");
      divKeywords.innerHTML = `${data.review}`;
      document.getElementById("result-from-Id").appendChild(divKeywords);
    });
}
var review_arr;
var length = 12;
var url = `http://localhost:5555/all`;
fetch(url)
  .then(function (response) {
    return response.json();
  })
  .then(function (data) {
    for (let index = 0; index < length; index++) {
      const element = data[index];
      var divById = document.createElement("DIV");
      divById.setAttribute("class", "review-item-list col-4");
      divById.innerHTML = 
      
      `<div class='card' style ='width: 18rem;'>
      <div class='card-body' >
      <textarea id='myInput_${element.reviewID}'  value='${element.review}' style='min-height:50%; width:100%;border:none;' disabled >${element.review}</textarea> 
      <input id='myInputH_${element.reviewID}' type='hidden' value="${element.reviewID}">
      <button type='button' id='edit_${element.reviewID}'  class='btn btn-warning btn-sm' style=' float: right;' onclick="clickEdit(${element.reviewID})">แก้ไข</button>
      <button hidden type='button' id='confirm_${element.reviewID}'  class='btn btn-success btn-sm' style='margin-right:2%; float: right;' onclick="clickConfirm(${element.reviewID})">ยืนยัน</button>
      </div>
      </div>`
      
      
      document.getElementById("result-list").appendChild(divById);
    }
  });

function clickEdit(params) {
  document.getElementById("myInput_" + params).disabled = false;
  document.getElementById("edit_" + params).setAttribute("hidden", true);
  document.getElementById("confirm_" + params).hidden = false;
}

function clickConfirm(params) {
  var textArea = document.getElementById("myInput_" + params);
  fetch(`http://localhost:5555/reviews/${params}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      review: textArea.value,
    }),
  })
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      textArea.value = data.review;
    });

  textArea.disabled = true;
  document.getElementById("edit_" + params).removeAttribute("hidden");
  document.getElementById("confirm_" + params).hidden = true;
}

function updateReview(inp, arr) {}
