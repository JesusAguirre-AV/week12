package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(
			int a, int b, int expected, boolean expectException) {
		
		if(!expectException) {
			assertThat(testDemo.addPositive(a,b)).isEqualTo(expected);
		}else {
			assertThatThrownBy(() -> 
			testDemo.addPositive(a, b))
			.isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
		assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
		assertThat(testDemo.addPositive(25, 25)).isEqualTo(50);
		assertThat(testDemo.addPositive(5, 4)).isEqualTo(9);
	}
	
	static Stream<Arguments> argumentsForAddPositive() {
		// formatter:off
		return Stream.of(
			arguments(2,4,6,false),
			arguments(8,8,16,false),
			arguments(0,0,0,true),
			arguments(0,5,5,true),
			arguments(2,0,2,true),
			arguments(-2,-4,-6,true)
		);
		// formatter:on
		
	}

	//Here is the parameterized test for my own method
	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsOwnTestMethod")
	void assertThatOwnTestMethodWorksWithParams(
			int a, int b, int c, int expected, boolean excpException) {
		if(!excpException) {
			assertThat(testDemo.ownTestMethod(a, b, c)).isEqualTo(expected);
		}else {
			assertThatThrownBy(() ->
			testDemo.ownTestMethod(a, b, c)).isInstanceOf(IllegalArgumentException.class);
		}
	}
	//Non-parameterized test for my method
	@Test
	void assertThatOwnTestWithoutParams() {
		assertThat(testDemo.ownTestMethod(3, 4, 5)).isEqualTo(12);
	}
	//Stream for my method
	static Stream<Arguments> argumentsOwnTestMethod(){
		return Stream.of(
				arguments(3,6,9,18,false),
				arguments(3,2,10,6,true)
				);
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
	
}
