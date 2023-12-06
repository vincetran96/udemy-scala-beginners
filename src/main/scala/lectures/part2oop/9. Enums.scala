package lectures.part2oop

object Enums extends App{
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    def openDocument(): Unit = {
      if (this == READ) println("Opening document")
      else println("No read")
    }
  }

  // Enums can take constructor args
  enum PermissionsWithBits(bits: Int) {
    case READ extends PermissionsWithBits(4)
    case WRITE extends PermissionsWithBits(2)
    case EXECUTE extends PermissionsWithBits(1)
    case NONE extends PermissionsWithBits(0)
  }

  object PermissionsWithBits {
    def fromBits(bits: Int): PermissionsWithBits = {
      if (bits == 4) PermissionsWithBits.READ
      else PermissionsWithBits.NONE
    }

    def doSomething(): Unit = println("Something")
  }

  val somePermission: Permissions = Permissions.READ
  somePermission.openDocument()
  println(s"Ordinal of SP: ${somePermission.ordinal}")

  val somePermission1: Permissions = Permissions.valueOf("WRITE")
  somePermission1.openDocument()

  val allPermissions: Array[Permissions] = Permissions.values
  println(s"All permissions: ${allPermissions.mkString("[", ", ", "]")}")

  val readBitPermission: PermissionsWithBits = PermissionsWithBits.fromBits(4)

}
