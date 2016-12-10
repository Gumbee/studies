# Algebra

## Monoids

#### Definition

A Monoid M \<M;•,e> is an algebraic structure that satisfies the following rules:

1. The binary operation • on the set M is associative
2. There exists a left/right neutral element e such that e•a = a or a•e=a. If both left and right neutral element exist, then it's simply called neutral element.

## Groups

A Group G \<G;•,⁻¹,e> is an algebraic structure that satisfies the following rules:

1. The binary operation • on the set G is associative
2. There exists a left/right neutral element e such that e•a = a or a•e=a. If both left and right neutral element exist, then it's simply called neutral element.
3. For each a of G, there exists an inverse element a⁻¹ such that the following equation is satisfied: a•a⁻¹ = a⁻¹•a = e

## Rings

A Ring R \<R;+,-,0,•,1> is an algebraic structure that satisfies the following rules:

1. \<R;+,-,0> is a group
2. \<R;•,1> is a monoid
3. a•(b+c) = a•b + a•c and (a+b)•c = a•c + b•c for all a,b,c (distributivity)

## Fields

A Field F is a Ring R with an additive group and a multiplicative group (instead of a multiplicative monoid). Therefore every element (except the additive identity) is guaranteed to have a inverse element.

## Finite Fields

A finite field (also Galois field) GF is a field that contains a limited (finite) number of elements. Any finite field contains exactly qⁿ elements where q is a prime number and n is a positive integer. 

## Usage

#### Create a group or a monoid

You can either create a group yourself by creating a class that implements the interface `Group<T>` or you can create an instance of an existing group-class. The Group ℤᵨ* where p is a prime can be instantiated the following way: 

```Java
// initialize a group of integers modulo p=7 (p must be prime or otherwise no multiplicative group can be
// constructed [if the group is additive and not multiplicative, p is not required to be a prime number])
ZModGroup modMult = new ZModGroup(7, ZModType.multiplicative);

// initialize an additive group of integers modulo p=12 (if the group
// is additive, then p doesn't have to be prime) 
ZModGroup modAdd = new ZModGroup(12, ZModType.additive);
```

Monoids are created analogously by using ZModMonoid instead of ZModGroup. The only difference is that in ZModMonoid the multiplicative monoid doesn't have to be modulo a prime.

#### Calculations in a group or monoid

```Java
// create a group modulo 23
ZModGroup modMult = new ZModGroup(23, ZModType.multiplicative);

// calculate the inverse of 18 (possible because we're in a group)
Integer inverse = modMult.inverse(18);
// mutliply 18 and it's inverse
Integer result = modMult.add(18, inverse);

// result is 1
System.out.println("Result of 18*" + inverse + " in the group modulo 23 is: " + result);
```

#### Create a finite field (Galois field)

```Java
// create two groups (modulo the same prime number so that they both operate on the same set)
ZModGroup modAdd = new ZModGroup(5, ZModType.additive);
ZModGroup modMult = new ZModGroup(5, ZModType.multiplicative);

// a Galois field requires an additive group and a multiplicative group (both
// must be operating on the same set)
GaloisField<Integer> GF = new GaloisField<>(modAdd, modMult);
```

#### Calculations in a finite field

```Java
// a finite field has two operations (adding from the additive group and multiplicating from the
// multiplicative group). Therefore we also have two identities (additive and multiplicative). If we
// multiplicate the additive identity with an arbitrary element of the field, we get the additve identity
// as result. Of course there are also two inverse elements (additive inverse and multiplicative inverse)
Integer additiveIdentity = GF.additiveIdentity();

Integer multiplicationResult = GF.mult(additiveIdentity, 4);
Integer additionResult = GF.add(2,3);
```

#### Define polynomials over a finite field

Polynomials can be defined over a finite field. The resulting algebraic structure is a extension field (if all polynomials are calculated modulo an irreducible polynomial). Since the resulting algebraic structure is itself a finite field, we can of course again define a polynomial over that field. In fact we can repeat this process for as long as we desire.

```Java
// define a polynomial over a Galois field
PolynomialField<Integer> PolynomialField = new PolynomialField<>(GF);
// define a polynomial over the polynomial field we constructed before
PolynomialField<Polynomial<Integer>> PolyPolynomialField = new PolynomialField<>(PolynomialField);
```