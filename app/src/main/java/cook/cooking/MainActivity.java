package cook.cooking;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<Recipe> recipeArrayList;
    RecipeAdapter adapter;

    //Создаем новый объект и вносим в него данные для приложения
    Recipe[] recipes = new Recipe[] {
            new Recipe("Запечённая рыба по-гречески",
                    "Не знаете, как вкусно и интересно приготовить рыбу? Тогда это то, что вам нужно! Рецепт очень простой и доступный, а запечённая рыба \"под шубой\" получается просто шик!",
                    "Филе любой белой рыбы - 700 грамм; томаты - 2 штуки; твердый сыр - 150 грамм; сметана - 200 грамм; зелень; чеснок по вкусу; соль, перец.",
                    "https://img1.russianfood.com/dycontent/images_upl/271/big_270466.jpg",
                    "https://r2---sn-gvnuxaxjvh-0atl.googlevideo.com/videoplayback?ipbits=0&itag=22&mm=31%2C29&ip=89.250.174.136&ms=au%2Crdu&mv=m&mt=1552993235&id=o-AM_DGnOja_fPjhgT_tBC7kxVMy-gQd76mlEfSmGDMteP&pl=23&ei=TsyQXOCrO8Lz7QTPzrf4CQ&c=WEB&expire=1553014959&key=yt6&mime=video%2Fmp4&pcm2cms=yes&initcwndbps=713750&signature=86E1DEDC7779023A095C5817261BEAA9BFE66742.3E90077FF57C08EE80ADD325CF5F1D354AE9DBD4&ratebypass=yes&mn=sn-gvnuxaxjvh-0atl%2Csn-gvnuxaxjvh-bvw6&txp=5432432&dur=130.496&lmt=1545941056924195&source=youtube&fvip=4&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&requiressl=yes&title=%D0%A8%D0%B8%D0%BA%D0%B0%D1%80%D0%BD%D0%B0%D1%8F%20%D0%A0%D1%8B%D0%B1%D0%BA%D0%B0%20%D0%BF%D0%BE%D0%B4%20%D0%A8%D1%83%D0%B1%D0%BE%D0%B9.%20%D0%A0%D1%8B%D0%B1%D0%B0%20%D0%BF%D0%BE-%D0%93%D1%80%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8.%20%D0%9F%D0%BE%D1%82%D1%80%D1%8F%D1%81%D0%B0%D1%8E%D1%89%D0%B5%20%D0%92%D0%BA%D1%83%D1%81%D0%BD%D0%BE%D0%B5%20%D0%91%D0%BB%D1%8E%D0%B4%D0%BE!"),
            new Recipe("Горбуша, запеченная в духовке",
                    "Горбушу, запеченную в духовке, готовить несложно, но в результате она получается вкусной, сочной и нежной! Попробуйте приготовить!",
                    "Горбуша - 1 шт.\n" +
                            "Сыр твёрдый - 100-150 г\n" +
                            "Томаты - 2 шт.\n" +
                            "Сметана - 200 г\n" +
                            "Чеснок - 2 зубчика (или по вкусу)\n" +
                            "Соль - по вкусу\n" +
                            "Перец черный молотый - по вкусу\n" +
                            "Приправы - по вкусу",
                    "https://img1.russianfood.com/dycontent/images_upl/283/big_282423.jpg",
                    "https://r1---sn-gvnuxaxjvh-0ate.googlevideo.com/videoplayback?pl=17&mime=video%2Fmp4&expire=1553018932&ip=95.68.147.85&key=yt6&lmt=1544139549144239&dur=233.592&ratebypass=yes&source=youtube&pcm2cms=yes&signature=B74EFD95823BAF4AFCF4274E125C6FD0D57C38F5.66E606D1A106102BAD52AA638FBEEE20E6DC00A0&requiressl=yes&ipbits=0&fvip=8&id=o-AHbQEoXlNN9SVmWAAYZYrmEgVGRMSZvbc1du9Bknu0fL&c=WEB&txp=5535432&initcwndbps=668750&mn=sn-gvnuxaxjvh-0ate%2Csn-gvnuxaxjvh-bvws&mm=31%2C29&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&ms=au%2Crdu&itag=22&ei=09uQXIOnNInYyQXn2ofIAQ&mv=m&mt=1552997252&title=%D0%A1%D0%BE%D1%87%D0%BD%D0%B0%D1%8F%20%D0%B8%20%D0%9D%D0%B5%D0%B6%D0%BD%D0%B0%D1%8F%20%D0%93%D0%BE%D1%80%D0%B1%D1%83%D1%88%D0%B0%20%D0%B2%20%D0%94%D1%83%D1%85%D0%BE%D0%B2%D0%BA%D0%B5.%20%D0%9C%D0%B0%D0%BA%D1%81%D0%B8%D0%BC%D0%B0%D0%BB%D1%8C%D0%BD%D0%BE%20%D0%9F%D1%80%D0%BE%D1%81%D1%82%D0%BE%D0%B9%20%D0%A0%D0%B5%D1%86%D0%B5%D0%BF%D1%82!"),
            new Recipe("Печенье \"Творожные сердечки\"",
                    "Рецепт творожного печенья в форме сердечек. Лимонная цедра придает приятный вкус, а сами печеньки получаются мягкие и пышные. Простой и быстрый рецепт, простая выпечка!",
                    "Творог - 200 г\n" +
                            "Масло сливочное - 100 г\n" +
                            "Мука - 1 стакан\n" +
                            "Сахар - 100 г\n" +
                            "Яйцо - 1 шт.\n" +
                            "Лимон - 0,5 шт.\n" +
                            "Сода - 0,5 ч. л.\n" +
                            "Ванилин - на кончике ножа\n" +
                            "Сахарная пудра - 2 ч. л.",
                    "https://img1.russianfood.com/dycontent/images_upl/249/big_248845.jpg",
                    "https://r2---sn-gvnuxaxjvh-0ate.googlevideo.com/videoplayback?source=youtube&ms=au%2Crdu&ei=UwmRXP60Gfrm7gTRgrD4BQ&pl=17&mv=m&ipbits=0&id=o-ABBuzJOgHLSoVx-WJYKmsAJicVmcasB_22n-aHARMKYb&mn=sn-gvnuxaxjvh-0ate%2Csn-gvnuxaxjvh-bvws&mm=31%2C29&pcm2cms=yes&ip=95.68.147.85&dur=287.880&c=WEB&fvip=1&lmt=1521235355200597&ratebypass=yes&requiressl=yes&initcwndbps=661250&key=yt6&mime=video%2Fmp4&mt=1553008847&itag=22&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&signature=3FA5B3715694A89A26A58E444357B66913045EE7.316A171A325FD48923802116F7571C06D3F1A653&expire=1553030579&title=%D0%9F%D0%95%D0%A7%D0%95%D0%9D%D0%AC%D0%95%20%D0%A2%D0%92%D0%9E%D0%A0%D0%9E%D0%96%D0%9D%D0%AB%D0%95%20%D0%A1%D0%95%D0%A0%D0%94%D0%95%D0%A7%D0%9A%D0%98%20_%20%D0%A2%D0%B2%D0%BE%D1%80%D0%BE%D0%B6%D0%BD%D0%BE%D0%B5%20%D0%BF%D0%B5%D1%87%D0%B5%D0%BD%D1%8C%D0%B5%20_%20%D0%92%D1%81%D0%B5%D0%B3%D0%B4%D0%B0%20%D0%92%D0%BA%D1%83%D1%81%D0%BD%D0%B0%D1%8F%20%D0%95%D0%B4%D0%B0"),
            new Recipe("Соус бешамель",
                    "Главный секрет соуса бешамель вовсе не в том, что он прост в приготовлении или одинаково хорошо подходит к мясным, рыбным и овощным блюдам. Главное, что соус бешамель является базовым для многих соусов. Соус бешамель может быть отличной заправкой для низкокалорийных салатов или необходимым ингредиентом в приготовлении, например, супов и других блюд.",
                    "Молоко - 900 мл\n" +
                            "Масло сливочное - 75 г\n" +
                            "Мука пшеничная - 75 г\n" +
                            "Перец черный молотый - 1/3 ч. л.\n" +
                            "Орех мускатный молотый - 1/3 ч. л.\n" +
                            "Соль - 1/2 ч. л.",
                    "https://img1.russianfood.com/dycontent/images_upl/265/big_264576.jpg",
                    "https://r2---sn-gvnuxaxjvh-0ate.googlevideo.com/videoplayback?pl=23&id=o-ABSuN2I5sZRy3viMUSe662fjAKx-vAPBLIA2EIMuaNCc&mv=m&mt=1553008963&ms=au%2Crdu&mn=sn-gvnuxaxjvh-0ate%2Csn-gvnuxaxjvh-bvwz&mm=31%2C29&ip=89.250.166.127&ipbits=0&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&requiressl=yes&fvip=2&source=youtube&lmt=1528923389023004&dur=97.314&ratebypass=yes&signature=1BF8BD7E65A529BFD339F4AFAB8B81655DFF433C.6FF9375C856AC9CCF6133957E8B23EC53609C6CE&itag=22&initcwndbps=661250&pcm2cms=yes&key=yt6&mime=video%2Fmp4&c=WEB&expire=1553030682&ei=ugmRXLy_AsHj7ASi64KoBQ&title=%D0%A1%D0%BE%D1%83%D1%81%20%D0%B1%D0%B5%D1%88%D0%B0%D0%BC%D0%B5%D0%BB%D1%8C.%20%D0%A0%D0%B5%D1%86%D0%B5%D0%BF%D1%82%20%D0%BE%D1%82%20%D0%92%D1%81%D0%B5%D0%B3%D0%B4%D0%B0%20%D0%92%D0%BA%D1%83%D1%81%D0%BD%D0%BE!"),
            new Recipe("Маринованные грибы на зиму",
                    "Предлагаю проверенный рецепт маринованных грибочков. Уже открыла и попробовала - грибы получились просто отменные! Для консервации взяла белые грибы и подберезовики в равных частях.",
                    "Грибы (белые и подберезовики) - 1 кг\n" +
                            "Чеснок - 3 зубчика\n" +
                            "*\n" +
                            "Для маринада:\n" +
                            "Вода - 1 л\n" +
                            "Соль - 1 ст. л.\n" +
                            "Сахар - 1 ч. л.\n" +
                            "Уксус 9% - 2,5 ст. л.\n" +
                            "Перец горошком - 5-8 шт.\n" +
                            "Гвоздика (по желанию) - 2 шт.\n" +
                            "Лавровый лист - 3 шт.\n" +
                            "Листья смородины - 4 шт.\n" +
                            "Укроп - 2 зонтика",
                    "https://img1.russianfood.com/dycontent/images_upl/274/big_273143.jpg",
                    "https://r1---sn-gvnuxaxjvh-0atl.googlevideo.com/videoplayback?requiressl=yes&signature=4A98AE6B512566EFBD8D472AC337178644333095.C7B78170E5CA192ADC4092BF9161781599C44CA7&ipbits=0&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&expire=1553030738&lmt=1532863333206949&key=yt6&initcwndbps=695000&c=WEB&ei=8gmRXLuoBMfJyQXYq6HwDw&id=o-AAnwmLml9SxfS9xOO5bzpoDLP3bPimEu4xCwGWICYTz8&ms=au%2Crdu&mt=1553009060&pl=23&mv=m&fvip=5&ratebypass=yes&ip=89.250.166.127&mm=31%2C29&source=youtube&mn=sn-gvnuxaxjvh-0atl%2Csn-gvnuxaxjvh-bvwz&itag=22&mime=video%2Fmp4&dur=162.911&pcm2cms=yes&title=%D0%9C%D0%90%D0%A0%D0%98%D0%9D%D0%9E%D0%92%D0%90%D0%9D%D0%9D%D0%AB%D0%95%20%D0%93%D0%A0%D0%98%D0%91%D0%AB%20%D0%9D%D0%90%20%D0%97%D0%98%D0%9C%D0%A3_%20%D0%9C%D0%B0%D1%80%D0%B8%D0%BD%D0%BE%D0%B2%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5%20%D0%B1%D0%B5%D0%BB%D1%8B%D0%B5%20%D0%B3%D1%80%D0%B8%D0%B1%D1%8B%20%D1%80%D0%B5%D1%86%D0%B5%D0%BF%D1%82"),
            new Recipe("Закусочный торт на праздничный стол",
                    "Такой закуски на моём столе ещё не было! Давно хотела попробовать этот рецепт закусочного торта и удивить своих гостей! И у меня получилось всё, как задумала, – вкусно и красиво! Как оказалось, рыбный закусочный торт готовится легко, а продукты все доступны. Готовьте на здоровье и порадуйте всех за праздничным столом!",
                    "Тесто слоёное - 500 г (2 пласта)\n" +
                            "*\n" +
                            "Для начинки №1:\n" +
                            "Рыба красная солёная - 200 г\n" +
                            "Огурцы маринованные (корнишоны) - 8 шт.\n" +
                            "Яйца отварные - 2 шт.\n" +
                            "Сыр российский - 70 г\n" +
                            "Укроп свежий рубленый - 1 ст. ложка\n" +
                            "Майонез - по вкусу\n" +
                            "Соль - по вкусу\n" +
                            "Перец чёрный молотый - по вкусу\n" +
                            "*\n" +
                            "Для начинки №2:\n" +
                            "Скумбрия горячего копчения - 200 г\n" +
                            "Сыр творожный - 150 г\n" +
                            "Сыр плавленый сливочный - 100 г\n" +
                            "Хрен (не крепкий) - 3 ст. ложки\n" +
                            "*\n" +
                            "Для покрытия:\n" +
                            "Сыр плавленый сливочный - 100 г\n" +
                            "Сыр творожный - 150 г\n" +
                            "*\n" +
                            "Для украшения:\n" +
                            "Рыба красная - 50-100 г\n" +
                            "Огурцы свежие - 1-2 шт.\n" +
                            "Лук зелёный - 1 небольшой пучок\n" +
                            "Горошек зелёный",
                    "https://img1.russianfood.com/dycontent/images_upl/296/big_295680.jpg",
                    "https://r1---sn-gvnuxaxjvh-0atl.googlevideo.com/videoplayback?dur=706.397&lmt=1544157887183465&mv=m&c=WEB&fvip=11&ratebypass=yes&pcm2cms=yes&clen=54455616&id=o-ALfNoOl1q1mmKcS7MiYjt_ANflOnc9spLP-JIIKmorRo&mm=31%2C29&mn=sn-gvnuxaxjvh-0atl%2Csn-gvnuxaxjvh-bvwz&ip=89.250.166.127&ei=hwqRXKDWEJLl7gSNq6DYAg&ms=au%2Crdu&ipbits=0&pl=23&source=youtube&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&signature=2E4C0C3A9EB4B4D50AC6AF89C519FC1B3005EA1D.16ADA2F108A649E28F4F60F7906D2BEDD3A1686A&txp=5531432&itag=18&mime=video%2Fmp4&gir=yes&key=yt6&expire=1553030887&mt=1553009133&requiressl=yes&initcwndbps=695000&title=%D0%93%D0%BE%D1%81%D1%82%D0%B8%20%D0%B0%D1%85%D0%BD%D1%83%D1%82!%20%D0%97%D0%B0%D0%BA%D1%83%D1%81%D0%BE%D1%87%D0%BD%D1%8B%D0%B9%20%D1%82%D0%BE%D1%80%D1%82%20%D0%BD%D0%B0%20%D0%BF%D1%80%D0%B0%D0%B7%D0%B4%D0%BD%D0%B8%D1%87%D0%BD%D1%8B%D0%B9%20%D1%81%D1%82%D0%BE%D0%BB!"),
            new Recipe("Домашняя лапша с томатным соусом",
                    "Домашняя лапша – это очень вкусное блюдо. Домашнюю лапшу подают с разными соусами. Предлагаем рецепт домашней лапши на желтках, с помидорным соусом.",
                    "Для теста:\n" +
                            "Мука - 250 г\n" +
                            "Желтки - 6 шт.\n" +
                            "Яйца - 1 шт.\n" +
                            "Масло растительное - 2 ст. ложки\n" +
                            "*\n" +
                            "Для соуса:\n" +
                            "Помидоры - 1 кг\n" +
                            "Лук - 1 шт.\n" +
                            "Чеснок - 1 зубок\n" +
                            "Масло оливковое или другое растительное - 50 г\n" +
                            "Соль - 1 ч. ложка\n" +
                            "Перец черный молотый - 0,5 ч. ложки",
                    "https://img1.russianfood.com/dycontent/images_upl/208/big_207211.jpg",
                    "https://r2---sn-gvnuxaxjvh-0atl.googlevideo.com/videoplayback?mime=video%2Fmp4&key=yt6&requiressl=yes&initcwndbps=688750&dur=609.059&expire=1553030984&fvip=4&c=WEB&mt=1553009244&ratebypass=yes&pcm2cms=yes&pl=23&id=o-AK9CUt7iM5dhP4-uVEGJ3jEKcecoR_DLb1IMlKAAIIfm&mm=31%2C29&mn=sn-gvnuxaxjvh-0atl%2Csn-gvnuxaxjvh-bvw6&source=youtube&ei=6AqRXJC0K8LHyQX7wJiQCA&ms=au%2Crdu&ipbits=0&mv=m&lmt=1471030456122103&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&signature=4A895B0DEB1E76ED74A3D7D9C7614FA4E03A3CA5.7E129FC602B5E9DD34FE61BB018C4C2A64645323&ip=89.250.166.127&itag=22&title=%D0%9B%D0%B0%D0%BF%D1%88%D0%B0%20%D1%81%20%D1%82%D0%BE%D0%BC%D0%B0%D1%82%D0%BD%D1%8B%D0%BC%20%D1%81%D0%BE%D1%83%D1%81%D0%BE%D0%BC%20-%20%D0%A0%D0%B5%D1%86%D0%B5%D0%BF%D1%82%20%D0%91%D0%B0%D0%B1%D1%83%D1%88%D0%BA%D0%B8%20%D0%AD%D0%BC%D0%BC%D1%8B"),
            new Recipe("Макароны по-летнему",
                    "Простой рецепт спагетти с соусом из летних овощей - паста (макароны) с кабачками.",
                    "Макароны (спагетти) - 450 г\n" +
                            "Помидоры - 1 кг\n" +
                            "Кабачки (цукини) - 2 шт.\n" +
                            "Морковь - 1 шт.\n" +
                            "Лук репчатый - 1 шт.\n" +
                            "Чеснок - 3 зубчика\n" +
                            "Томатный соус - 2 ст. ложки\n" +
                            "Яйца - 3 шт.\n" +
                            "Сыр пармезан натертый - 1 стакан + для подачи\n" +
                            "Итальянские травы - 2 ч. ложки\n" +
                            "Хлопья перца чили - 1/3 ч. ложки\n" +
                            "Базилик свежий - по вкусу\n" +
                            "Масло оливковое - 2-3 ст. ложки\n" +
                            "Соль - по вкусу\n" +
                            "Перец черный молотый (по желанию) - по вкусу",
                    "https://img1.russianfood.com/dycontent/images_upl/273/big_272026.jpg",
                    "https://r1---sn-gvnuxaxjvh-0ate.googlevideo.com/videoplayback?itag=22&pcm2cms=yes&lmt=1532683773032532&key=yt6&fvip=6&ratebypass=yes&signature=0BD059BEE8A3E45BB38EE3732367CF3C0CDECDB1.96B37D5AD1F6E0EFCB0256D40613675803D42BD0&c=WEB&source=youtube&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpcm2cms%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&expire=1553031027&dur=799.927&ei=EguRXIHsOIi2yQXG2raYCA&initcwndbps=645000&requiressl=yes&ip=89.250.166.127&ms=au%2Crdu&mv=m&mt=1553009335&pl=23&id=o-ACIAKJAtKsZVvKy9BTLJcROHRL8fYyDPHzx6DK6_afnJ&mime=video%2Fmp4&mn=sn-gvnuxaxjvh-0ate%2Csn-gvnuxaxjvh-bvw6&mm=31%2C29&ipbits=0&title=%D0%9C%D0%B0%D0%BA%D0%B0%D1%80%D0%BE%D0%BD%D1%8B%20%D0%BF%D0%BE-%D0%BB%D0%B5%D1%82%D0%BD%D0%B5%D0%BC%D1%83%20_%20%D0%90%D0%A1%D0%9C%D0%A0%20_%20%D0%A0%D0%B5%D1%86%D0%B5%D0%BF%D1%82"),

    };

    //Связываем данные
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setIcon(R.drawable.ic_action_logo);
            actionBar.setTitle("    " + actionBar.getTitle());
        }

        recipeArrayList = new ArrayList<>(Arrays.asList(recipes));
        adapter = new RecipeAdapter(this, recipeArrayList);

        ListView movieListView = (ListView) findViewById(R.id.movieListView);
        movieListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        SearchView searchView = searchItem.getActionView() as SearchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Поиск", newText);

                recipeArrayList.clear();

                if (newText.equals("")) {
                    recipeArrayList.addAll(Arrays.asList(recipes));
                } else {
                    for (Recipe recipe : recipes) {
                        if (recipe.getName().toLowerCase().contains(newText.toLowerCase())) {
                            recipeArrayList.add(recipe);
                        }
                    }
                }

                adapter.notifyDataSetChanged();

                return false;
            }
        });
            return super.onCreateOptionsMenu(menu);
    }

}
