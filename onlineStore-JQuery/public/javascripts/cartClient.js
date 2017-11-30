$(document).ready(function () {
    $.get('/users/getCurrentUser', function(data){
        var currentUser = data;

        if(currentUser)
        {
            $(".displayLogOut").css('display','block');       
        }
        else
        {
            $(".displayRedisterAndLogin").css('display','block');      
        }
    });
    
    var quantityBtn = $(".qtyNumber").on("change", updateQuantity);
    var deleteProductBtn = $(".deleteProductBtn").on("click", deleteProduct);

    function updateQuantity()
    {
       var quantityBtn = $(this);
       var qty = parseInt(quantityBtn.val());
       var price = parseFloat(quantityBtn.parent().next().html());
       var sum = qty*price;        
       
       quantityBtn.parent().next().next().html(sum.toFixed(2));        
       calcTotalSumCart();

       var productObj = {
                            name: $(this).parent().prev().html(),
                            newQuantity: qty
                        };

       $.get('/carts/changeQuantity',productObj);
    }

    function deleteProduct()
    {
        $(this).parent().parent().remove();
        calcTotalSumCart();
        var productObj = {
                            name: $(this).parent().next().html()
                         };
                    
        $.get('/carts/delete',productObj);
    }
    

    function calcTotalSumCart()
    {
       var sumAll = 0;

       $('.totalProductPrice').each(function()
       {
          sumAll+= parseFloat($(this).html());
       });
     
       $('#sumAll').html(sumAll.toFixed(2));
    }

    window.onbeforeunload = function (e) {
        window.location.hash = "/";
}
});