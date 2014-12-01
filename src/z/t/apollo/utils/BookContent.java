package z.t.apollo.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookContent {
	public static class Book {
		public Integer id;
		public String title;
		public String desc;

		public Book(Integer id, String title, String desc) {
			this.id = id;
			this.title = title;
			this.desc = desc;
		}

		@Override
		public String toString() {
			return title;
		}
	}

	// 使用list记录book对象
	public static List<Book> ITEMS = new ArrayList<Book>();
	// 使用map记录book对象
	public static Map<Integer, Book> ITEM_MAP = new HashMap<Integer, Book>();
	// 使用静态初始化代码，讲book对象添加到list集合、map集合中
	static {
		addItem(new Book(1, "演员的自我修养", "语言清晰，口齿清楚。选择擅长，发挥极致。"));
		addItem(new Book(2, "程序员的自我修养", "为什么这么低端，找到编码的感觉，让数据来说话。"));
		addItem(new Book(3, "逗比的自我修养", "屌丝的几大特征,身体的修炼, 性格的重新塑造,备胎的自我修养,为人处世篇。"));
	}

	private static void addItem(Book book) {
		ITEMS.add(book);
		ITEM_MAP.put(book.id, book);
	}
}
