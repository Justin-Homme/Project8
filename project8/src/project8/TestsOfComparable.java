package project8;

import static org.junit.Assert.*;


import org.junit.jupiter.api.Test;

/**
 * These tests will mostly use the BookBibEntry class, but the functionality
 * we're testing should exist in the AbstractBibEntry class, so JournalBibEntry
 * should work as well.
 * @author gosnat
 * @version Spring 2021
 */
class TestsOfComparable {

	/** Make sure you declared that you implement the Comparable interface
	 * and didn't just supply a compareTo() method
	 */
	@Test
	void testInterface() {
		BookBibEntry b = new BookBibEntry("title", null, null, null, null, false,
				"publisher", 2000, "city", "state");
		assertTrue(b instanceof Comparable<?>);
		assertTrue(b.compareTo(b) == 0);
	}
	
	/**
	 * An easy test: make sure obviously different first authors are sorted correctly
	 */
	@Test
	void testDifferentFirstAuthors() {
		BookBibEntry b1 = new BookBibEntry("title", "James", "Holden", null, null, false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("title", "Chrisjen", "Avasarala", null, null, false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) > 0 );
		assertTrue(b2.compareTo(b1) < 0);
		
	}
	
	/**
	 * If the last names are the same, use first names instead
	 */
	@Test
	void testSameLastName() {
		BookBibEntry b1 = new BookBibEntry("title", "Arjun", "Avasarala", null, null, false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("title", "Chrisjen", "Avasarala", null, null, false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) < 0);
		assertTrue(b2.compareTo(b1) > 0);
	}
	
	/**
	 * Make sure you're comparing past the first letter
	 * The String.compareTo() method should be handling all of this for you
	 */
	@Test
	void testBeyondFirstLetter() {
		BookBibEntry b1 = new BookBibEntry("title", "Julie", "Mao", null, null, false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("title", "Joe", "Miller", null, null, false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) < 0);
		assertTrue(b2.compareTo(b1) > 0);
	}
	
	
	/**
	 * If the first author is the same, use the second author to break the tie
	 */
	@Test 
	void testSameFirstAuthorDifferentSecondAuthor() {
		BookBibEntry b1 = new BookBibEntry("title", "Naomi", "Nagata", "Amos", "Burton", false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("title", "Naomi", "Nagata", "Alex", "Kamal", false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) < 0);
		assertTrue(b2.compareTo(b1) > 0);
	}
	
	/**
	 * If no authors are included, compare using the title instead
	 */
	@Test
	void testFirstAuthorvsTitle() {
		BookBibEntry b1 = new BookBibEntry("Martian Tactics", null, null, null, null, false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("Title", "Roberta", "Draper", null, null, false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) > 0);
		assertTrue(b2.compareTo(b1) < 0);
	}
	
	/**
	 * Should be able to compare sources with different numbers of authors
	 */
	@Test
	void testOneAuthorvsTwoAuthors() {
		BookBibEntry b1 = new BookBibEntry("title", "Naomi", "Nagata", "Amos", "Burton", false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("title", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) < 0);
		assertTrue(b2.compareTo(b1) > 0);
	}
	
	/**
	 * These two books are identical
	 */
	@Test
	void testFullTie() {
		BookBibEntry b1 = new BookBibEntry("title", "Naomi", "Nagata", "Amos", "Burton", false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("title", "Naomi", "Nagata", "Amos", "Burton", false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) == 0);
		assertTrue(b2.compareTo(b1) == 0);
	}
	
	/**
	 * Make sure not having a second author doesn't break anything
	 */
	@Test
	void testNoSecondAuthorTie() {
		BookBibEntry b1 = new BookBibEntry("title", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("title", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) == 0);
		assertTrue(b2.compareTo(b1) == 0);
	}
	
	/**
	 * Make sure that not having a second author doesn't prevent checking the title
	 */
	@Test
	void testNoSecondAuthorDifferentTitle() {
		BookBibEntry b1 = new BookBibEntry("AAA", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("ZZZ", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) < 0);
		assertTrue(b2.compareTo(b1) > 0);
	}
	
	/**
	 * These tests are just to snag anyone who's taking an inappropriate short cut
	 */
	@Test
	void testOnevsTwoAuthorTitleTie() {
		BookBibEntry b1 = new BookBibEntry("Barton Amos", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("title", "Naomi", "Nagata", "Amos", "Burton", false,
				"publisher", 2000, "city", "state");
		assertTrue(b2.compareTo(b1) > 0);
		
		b1 = new BookBibEntry("Barton, Amos", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		b2 = new BookBibEntry("title", "Naomi", "Nagata", "Amos", "Burton", false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) < 0);
		assertTrue(b2.compareTo(b1) > 0);
		
		b1 = new BookBibEntry("BartonAmos", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		b2 = new BookBibEntry("title", "Naomi", "Nagata", "Amos", "Burton", false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) < 0);
		assertTrue(b2.compareTo(b1) > 0);
	}
	
	/**
	 * These tests are to make sure people treat nulls appropriately
	 */
	@Test
	void testNulls() {
		BookBibEntry b1 = new BookBibEntry("nullnulltitle", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		BookBibEntry b2 = new BookBibEntry("title", "Naomi", "Nagata", "Amos", "Burton", false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) > 0);
		assertTrue(b2.compareTo(b1) < 0);
		
		b1 = new BookBibEntry(" null null title", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		b2 = new BookBibEntry("title", "Naomi", "Nagata", "Amos", "Burton", false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) < 0);
		assertTrue(b2.compareTo(b1) > 0);
		
		b1 = new BookBibEntry("   title", "Naomi", "Nagata", null, null, false,
				"publisher", 2000, "city", "state");
		b2 = new BookBibEntry("title", "Naomi", "Nagata", "Amos", "Burton", false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) < 0);
		assertTrue(b2.compareTo(b1) > 0);
		
		b1 = new BookBibEntry("ABCs of Space Flight", null, null, null, null, false,
				"publisher", 2000, "city", "state");
		b2 = new BookBibEntry("Zero Gravity Cooking", null, null, null, null, false,
				"publisher", 2000, "city", "state");
		assertTrue(b1.compareTo(b2) < 0);
		assertTrue(b2.compareTo(b1) > 0);
	}
	
	/**
	 * Make sure you can compare a Book and a Journal Article
	 */
	@Test
	void testBookVsJournal()  {
		BookBibEntry b1 = new BookBibEntry("title", "James", "Holden", null, null, false,
				"publisher", 2000, "city", "state");
		JournalBibEntry j2 = new JournalBibEntry("title", "Chrisjen", "Avasarala", null, null, false,
				"journal", 2020, 6, 3, 99, 101, "doistring");
		assertTrue(b1.compareTo(j2) > 0);
		assertTrue(j2.compareTo(b1) < 0);
	}

}