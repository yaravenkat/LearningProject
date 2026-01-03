# Top 30 Java Interview Programs (Array + Java 8)

This file contains 30 recently asked Java interview programs with both **Core Java / Array approach** and **Java 8 Streams approach**.

---

## 1. Third Distinct Maximum Number
**Problem:** Return the third distinct maximum number. If it doesn't exist, return the maximum.
**Input:** [3,2,1]  **Output:** 1

### Core Java
```java
Long first=null, second=null, third=null;
for(int n: nums){
    long x=n;
    if(x==first||x==second||x==third) continue;
    if(first==null||x>first){ third=second; second=first; first=x; }
    else if(second==null||x>second){ third=second; second=x; }
    else if(third==null||x>third){ third=x; }
}
return third==null?first.intValue():third.intValue();
```

### Java 8
```java
Arrays.stream(nums).distinct().boxed()
.sorted((a,b)->b-a).skip(2)
.findFirst().orElse(Arrays.stream(nums).max().getAsInt());
```

---

## 2. Remove Duplicates from Sorted Array
**Input:** [1,1,2,2,3]  **Output:** [1,2,3]

### Core Java
```java
int index=1;
for(int i=1;i<nums.length;i++)
    if(nums[i]!=nums[i-1]) nums[index++]=nums[i];
```

### Java 8
```java
int[] result = Arrays.stream(nums).distinct().toArray();
```

---

## 3. Reverse an Array
**Input:** [1,2,3,4]  **Output:** [4,3,2,1]

### Core Java
```java
int l=0,r=nums.length-1;
while(l<r){
    int temp=nums[l];
    nums[l++]=nums[r];
    nums[r--]=temp;
}
```

### Java 8
```java
int[] reversed = IntStream.rangeClosed(1,nums.length)
.map(i->nums[nums.length-i]).toArray();
```

---

## 4. Find Maximum Element
**Input:** [5,2,9,1]  **Output:** 9

### Core Java
```java
int max=nums[0];
for(int n:nums) if(n>max) max=n;
```

### Java 8
```java
int max = Arrays.stream(nums).max().getAsInt();
```

---

## 5. Find Minimum Element
**Input:** [5,2,9,1]  **Output:** 1

### Core Java
```java
int min=nums[0];
for(int n:nums) if(n<min) min=n;
```

### Java 8
```java
int min = Arrays.stream(nums).min().getAsInt();
```

---

## 6. Second Largest Number
**Input:** [10,5,20,8]  **Output:** 10

### Core Java
```java
int f=Integer.MIN_VALUE,s=Integer.MIN_VALUE;
for(int n:nums){
    if(n>f){ s=f; f=n; }
    else if(n>s && n!=f) s=n;
}
```

### Java 8
```java
int second = Arrays.stream(nums).distinct().boxed()
.sorted(Collections.reverseOrder()).skip(1).findFirst().get();
```

---

## 7. Move Zeros to End
**Input:** [0,1,0,3,12]  **Output:** [1,3,12,0,0]

### Core Java
```java
int index=0;
for(int n:nums) if(n!=0) nums[index++]=n;
while(index<nums.length) nums[index++]=0;
```

### Java 8
```java
int[] result = IntStream.concat(
Arrays.stream(nums).filter(n->n!=0),
Arrays.stream(nums).filter(n->n==0)
).toArray();
```

---

## 8. Check Palindrome Array
**Input:** [1,2,2,1]  **Output:** true

### Core Java
```java
boolean isPalin=true;
for(int i=0;i<nums.length/2;i++)
    if(nums[i]!=nums[nums.length-i-1]){ isPalin=false; break; }
```

### Java 8
```java
boolean isPalin = IntStream.range(0,nums.length/2)
.allMatch(i->nums[i]==nums[nums.length-1-i]);
```

---

## 9. Sum of Array Elements
**Input:** [1,2,3,4]  **Output:** 10

### Core Java
```java
int sum=0;
for(int n:nums) sum+=n;
```

### Java 8
```java
int sum = Arrays.stream(nums).sum();
```

---

## 10. Linear Search
**Input:** [5,3,7,1], key=7  **Output:** 2

### Core Java
```java
int index=-1;
for(int i=0;i<nums.length;i++)
    if(nums[i]==key){ index=i; break; }
```

### Java 8
```java
int index = IntStream.range(0,nums.length)
.filter(i->nums[i]==key).findFirst().orElse(-1);
```

---

## 11. Binary Search (Sorted Array)
**Input:** [1,3,5,7], key=5  **Output:** 2

### Core Java
```java
int l=0,r=nums.length-1;
while(l<=r){
    int m=(l+r)/2;
    if(nums[m]==key) return m;
    else if(nums[m]<key) l=m+1;
    else r=m-1;
}
```

### Java 8
```java
Arrays.binarySearch(nums,key);
```

---

## 12. Find Missing Number
**Input:** [3,0,1]  **Output:** 2

### Core Java
```java
int sum=n*(n+1)/2;
for(int x:nums) sum-=x;
```

### Java 8
```java
int missing = n*(n+1)/2 - Arrays.stream(nums).sum();
```

---

## 13. Find Duplicate Number
**Input:** [1,3,4,2,2]  **Output:** 2

### Core Java
```java
Set<Integer> set=new HashSet<>();
for(int x:nums) if(!set.add(x)) System.out.println(x);
```

### Java 8
```java
Set<Integer> set=new HashSet<>();
Arrays.stream(nums).filter(x->!set.add(x))
.findFirst().ifPresent(System.out::println);
```

---

## 14. Rotate Array by K
**Input:** [1,2,3,4,5], k=2  **Output:** [4,5,1,2,3]

### Core Java
```java
Collections.rotate(Arrays.asList(nums),k);
```

### Java 8
```java
int[] rotated = IntStream.range(0,nums.length)
.map(i->nums[(i+k)%nums.length]).toArray();
```

---

## 15. Merge Two Arrays
**Input:** [1,3] and [2,4]  **Output:** [1,3,2,4]

### Core Java
```java
int[] c = new int[a.length+b.length];
System.arraycopy(a,0,c,0,a.length);
System.arraycopy(b,0,c,a.length,b.length);
```

### Java 8
```java
int[] merged = IntStream.concat(Arrays.stream(a), Arrays.stream(b)).toArray();
