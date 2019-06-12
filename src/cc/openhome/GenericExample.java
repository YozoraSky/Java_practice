package cc.openhome;

public class GenericExample {
	public static void main(String args[]) {
		GenericExample test = new GenericExample();
		GenericExample.Generic<Boolean> generic;
		generic = test.new Generic<Boolean>();
		generic.setFoo(false);
		
//		使用通配符，不能set值給它 除非set的是null
//		 若要set值給它，可以使用Object
		GenericExample.Generic<?> generic2;
		generic2 = test.new Generic<Integer>(123);
		generic2 = test.new Generic<Boolean>(true);
		System.out.println(generic2.getFoo());
		
		GenericExample.Generic<Object> generic3;
		generic3 = test.new Generic<Object>();
		generic3.setFoo(123);
		generic3 = test.new Generic<Object>();
		generic3.setFoo(true);
	}
	public class Generic<T>{
		private T foo;
		
		public Generic() {
			
		}
		
		public Generic(T foo) {
			this.foo = foo;
		}
		  
		public void setFoo(T foo) {
			this.foo = foo;
		}
		  
		public T getFoo() {
			return foo;
		}
		public <E> void doSomething(T foo, E coo) {
//			  doSomething
		}
	}
}
