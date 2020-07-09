import ...
@Pkg("TEST_PKG")
@PkgFuncRes("TEST_FUNC_TT:SomeClass")
 class Test {aaa



//-------------------GENSTART-------------------
public class testPkg {
   public void test1(Double inVar){proc("TEST1", inVar);}
   public void test2(){proc("TEST2");}
   public String testFunc1(Double inVar){return func("TEST_FUNC1", String.class, inVar);} 

   public String testFunc2(Double inVar){return func("TEST_FUNC2", String.class, inVar);} 

   public void testFuncRc(Double inVar){proc("TEST_FUNC_RC", inVar);}
   public TEST_PKG_rec0 testFuncRc2(Double inVar){return func("TEST_FUNC_RC2", TEST_PKG_rec0.class, inVar);} 
 @Data
class TEST_PKG_rec0{ 
   Double id;
   String name;
}
   public TEST_PKG_rec0 testFuncTt(Double inVar){return func("TEST_FUNC_TT", TEST_PKG_rec0.class, inVar);} 
 @Data
class TEST_PKG_rec0{ 
   Double id;
   String name;
}
}
//-------------------GENEND-------------------
}