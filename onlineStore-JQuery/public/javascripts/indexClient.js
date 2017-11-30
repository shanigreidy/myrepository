$(document).ready(function () {
    var numberOfProductsInCart;

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

    $.get('/carts/getNumberOfProductsInCart',function(data){
        var numberOfProductsInCartStr = data;
        if(numberOfProductsInCartStr)
        {
            numberOfProductsInCart = parseInt(numberOfProductsInCartStr);
            if(numberOfProductsInCart > 0)
            {
                $("#numberOfProductsInCart").html(numberOfProductsInCart);
            }
        }
     });

    $(".cartIconAddToCart").on("click", addProductToCart);

    function addProductToCart()
    {
        var productPathArr = splitPathBySlash(this.value);
        var productObj = { category: productPathArr[0],
                           subCategory: productPathArr[1],
                           product: productPathArr[2]
                         };
        

        $.get('/products/getSelectedProduct',productObj,function(data){
            var product = data;
            if(product)
            {
                $("#numberOfProductsInCart").html(++numberOfProductsInCart);
            }
            $.get('/carts/addProductToCart',product);
       
        });
    }

    function splitPathBySlash(productPath)
    {
        return productPath.split("/");
    }

});