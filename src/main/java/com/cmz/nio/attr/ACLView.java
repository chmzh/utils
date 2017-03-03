package com.cmz.nio.attr;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.AclEntryType;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public class ACLView {

	public static void main(String[] args) {
		test3();
	}

	/**
	 * Read an ACL Using Files.getFileAttributeView()
	 */
	private static void test1() {
		List<AclEntry> acllist = null;
		Path path = Paths.get("story.txt");
		AclFileAttributeView aclview = Files.getFileAttributeView(path, AclFileAttributeView.class);
		try {
			acllist = aclview.getAcl();
			System.out.println(acllist);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Read an ACL Using Files.getAttribute()
	 */
	private static void test2() {
		List<AclEntry> acllist = null;
		Path path = Paths.get("story.txt");
		try {
			acllist = (List<AclEntry>) Files.getAttribute(path, "acl:acl", NOFOLLOW_LINKS);
			System.out.println(acllist);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/**
	 * Grant a New Access in an ACL
	 */
	private static void test3(){
		try {
			Path path = Paths.get("story.txt");
		    //Lookup for the principal
		    UserPrincipal user = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("chenmingzhou");
		    		//Get the ACL view
		    	    AclFileAttributeView view = Files.getFileAttributeView(path,
		    	                                             AclFileAttributeView.class);
		    	    //Create a new entry
		    	    AclEntry entry = AclEntry.newBuilder().setType(AclEntryType.ALLOW).
		    	               setPrincipal(user).setPermissions(AclEntryPermission.READ_DATA,
		    	               AclEntryPermission.APPEND_DATA).build();
		    	    //read ACL
		    	    List<AclEntry> acl = view.getAcl();
		    	    //Insert the new entry
		    	    acl.add(0, entry);
		    	    //rewrite ACL
		    	    view.setAcl(acl);
		    	    //or, like this
		    	    //Files.setAttribute(path, "acl:acl", acl, NOFOLLOW_LINKS);
		    	} catch (IOException e) {
		    	    System.err.println(e);
		    	}
	}
}
