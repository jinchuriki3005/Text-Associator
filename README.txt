Max Tran, CSE 373, HW#3
2/12/2017
1) I got this sentence accost Middle East ourselves is play so as to devise working principle and occupy picnic near token structures.
I thought this is hilarious.

2) 1. the starting capacity of my textAssociator should be a prime number and since myClient requires only 20 associations so 17 is a
      perfect number for starting capacity
   2. I think if the load factor get aboves 0.75, we should expand the hashTable because it's not completely close to 1 such that it will
      affect the runtime nor is it 
   3. It should be at least as twice as big as the old array in order to be efficient when it comes to runtime.

3) I use int index = Math.abs(word.hashCode() % PRIMES_TABLE[sizeIndex]); for my hashFunction, the PRIMES_TABLE contains a list of prime 
numbers such that the next prime number is at least twice as big as the previous one. This will be useful when it comes to expanding the
hashTable. We also know that prime number is useful when it comes to probing and rehashing. It will reduce likelihood of collisions due
to patterns in data. No there aren't any alternate hashFunction that I considered. In my opinions, prime numbers have some unique properties 
that prove to be useful when it comes to storing data and cryptography. I like the hashCode() function but in order to reudce likelihood of 
collisions, I mod it with a prime number. I think it's a good hashCode because it is multiplied by 31, an odd prime number. If it were even,
and the multiplication overflowed, information would be lost, as multiplication by 2 is equivalent to shifting. I read online that 31 has a 
nice property that the multiplication can be replaced by a shift and a subtraction for better performance 31 * i == (i << 5) - i
This of course is a good thing since nowadays, modern VMs do this sort of optimization automatically.
public int hashCode() { 
 int hash = 0;
 for (int i = 0; i < this.length(); i++) {
 hash = 31 * hash + this.charAt(i);
 }
 return hash;
} 

4) 
I would choose quadratic probing since it will prevent primary clustering and the values will be more spread out so the average number of probes
required will be less in the quadratic case. The only problem it has is secondary clustering or infinite loop. However, it's very rare for two words
to have the same ASCII value and be associated with each other thus I don't think secondary clustering will have any effect on textAssociator.
My code would change at the getAssociation(), we now have to do a reverse computing on the hashFunction in order to retrieve the associated words. 
For example, the hashFunction would give indexes to some different related words. However, these values will be spread out so that the average of probes
is a lot less than linear probing. Not only that, as we add more words and association to the textAssociator, we will have more and more data, and if we 
use linear probing, these values will then form a cluster, and the bigger the cluster is, the faster it forms. As a result, it will reduces the performance
severely. Quadratic probing allows textAssociator to have a faster runtime. In conclusion, besides separate chaining, I think quadratic probing would be 
the best bet for textAssociator since it reduces primary clustering, requires less probing, perform a lot better than linear probing when more and more 
values are added. 