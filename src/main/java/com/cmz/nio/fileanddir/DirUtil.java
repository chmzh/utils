package com.cmz.nio.fileanddir;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DirUtil {
	public static void main(String[] args) {
		list3();
	}

	/**
	 * Listing File System Root Directories
	 */
	private static void test1() {
		Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
		for (Path name : dirs) {
			System.out.println(name);
		}
	}

	/**
	 * Creating a New Directory
	 */
	private static void test2() {
		Path newdir = FileSystems.getDefault().getPath("test");
		try {
			Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
			FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
			Files.createDirectory(newdir, attr);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * 一次创建多个文件夹
	 */
	private static void test3() {
		Path newdir = FileSystems.getDefault().getPath("test1", "test2");
		try {
			Files.createDirectories(newdir);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Listing the Entire Content
	 */
	private static void list1() {
		Path path = Paths.get("src");
		// no filter applied
		System.out.println("\nNo filter applied:");
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Listing the Content by Applying a Glob Pattern • *: Represent (match) any
	 * number of characters, including none. • **: Similar to *, but cross
	 * directories’ boundaries. • ?: Represent (match) exactly one character. •
	 * {}: Represent a collection of subpatterns separated by commas. For
	 * example, {A,B,C} matches A, B, or C. • []: Convey a set of single
	 * characters or a range of characters if the hyphen character is present.
	 * Some common examples include the following: • [0-9]: Matches any digit •
	 * [A-Z]: Matches any uppercase letter • [a-z,A-Z]: Matches any uppercase or
	 * lowercase letter • [12345]: Matches any of 1, 2, 3, 4, or 5 • Within the
	 * square brackets, *, ?, and \ match themselves. • All other characters
	 * match themselves. • To match *, ?, or the other special characters, you
	 * can escape them by using the backslash character, \. For example, \\
	 * matches a single backslash, and \? matches the question mark.
	 */
	private static void list2() {
		Path path = Paths.get("src");
		// glob pattern applied
		System.out.println("\nGlob pattern applied:");
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*.{java,txt}")) {
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Listing the Content by Applying a User-Defined Filter
	 */
	private static void list3() {
		Path path = Paths.get("src");
		// user-defined filter - only directories are accepted
		DirectoryStream.Filter<Path> dir_filter = new DirectoryStream.Filter<Path>() {
			public boolean accept(Path path) throws IOException {
				return (Files.isDirectory(path, NOFOLLOW_LINKS));
			}
		};
		// Filter that accepts only files/directories larger than 200KB:
		DirectoryStream.Filter<Path> size_filter = new DirectoryStream.Filter<Path>() {
			public boolean accept(Path path) throws IOException {
				return (Files.size(path) > 204800L);
			}
		};

		// Filter that accepts only files modified in the current day:
		DirectoryStream.Filter<Path> time_filter = new DirectoryStream.Filter<Path>() {
			public boolean accept(Path path) throws IOException {
				long currentTime = FileTime.fromMillis(System.currentTimeMillis()).to(TimeUnit.DAYS);
				long modifiedTime = ((FileTime) Files.getAttribute(path, "basic:lastModifiedTime", NOFOLLOW_LINKS))
						.to(TimeUnit.DAYS);
				if (currentTime == modifiedTime) {
					return true;
				}
				return false;
			}
		};
		// Filter that accepts only hidden files/directories:
		DirectoryStream.Filter<Path> hidden_filter = new DirectoryStream.Filter<Path>() {
			public boolean accept(Path path) throws IOException {
				return (Files.isHidden(path));
			}
		};
		System.out.println("\nUser defined filter applied:");
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, dir_filter)) {
			for (Path file : ds) {
				System.out.println(file.getFileName());
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
